package rs.ftn.poslovna.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.AnalitikaIzvoda;
import rs.ftn.poslovna.domain.DnevnoStanjeRacuna;
import rs.ftn.poslovna.domain.MedjubankarskiNalog;
import rs.ftn.poslovna.domain.PravnoLice;
import rs.ftn.poslovna.domain.Racun;
import rs.ftn.poslovna.dto.AnalitikaDto;
import rs.ftn.poslovna.repository.AnalatikaIzvodaRepository;
import rs.ftn.poslovna.repository.DnevnoStanjeRepository;
import rs.ftn.poslovna.repository.MedjubankarskiNalogRepository;
import rs.ftn.poslovna.repository.NaseljenaMestaRepository;
import rs.ftn.poslovna.repository.RacunRepository;
import rs.ftn.poslovna.repository.ValutaRepository;
import rs.ftn.poslovna.repository.VrstaPlacanjaRepository;
import rs.ftn.poslovna.service.AnalitikaIzvodaService;

@Service
public class AnalitikaIzvodaServiceImpl implements AnalitikaIzvodaService {

	@Autowired
	private AnalatikaIzvodaRepository analatikaIzvodaRepository;

	@Autowired
	private MedjubankarskiNalogRepository medjubankarskiNalogRepository;

	@Autowired
	private VrstaPlacanjaRepository vrstaPlacanjaRepository;

	@Autowired
	private DnevnoStanjeRepository dnevnoStanjeRepository;

	@Autowired
	private ValutaRepository valutaRepository;

	@Autowired
	private NaseljenaMestaRepository naseljenaMestaRepository;

	@Autowired
	private RacunRepository racunRepository;
	

	@Override
	public AnalitikaDto getOne(long id) {
		AnalitikaIzvoda analitikaIzvoda = analatikaIzvodaRepository.getOne(id);
		
		if (analitikaIzvoda == null) {			
			return null;
		} else {
			return new AnalitikaDto(analitikaIzvoda);
		}
	}

	
	@Override
	public List<AnalitikaDto> getAll() {
		return analatikaIzvodaRepository.findAll().stream().map(AnalitikaDto::new).collect(Collectors.toList());
	}

	@Override
	public AnalitikaDto add(AnalitikaDto analitikaDto) {
		Racun racunDuznika = racunRepository.findByBrojRacuna(analitikaDto.getRacunDuznika());
		Racun racunPoverioca = racunRepository.findByBrojRacuna(analitikaDto.getRacunPoverioca());

		if (racunDuznika == null && racunPoverioca == null) {
			return null;
		}

		if (racunPoverioca != null && racunDuznika == null && (racunPoverioca.isValidan() == true)) {
			System.out.println("Uplata");
			uplata(analitikaDto, racunPoverioca);
		} else if (racunPoverioca != null && racunDuznika != null && ((racunPoverioca.isValidan() == true) && (racunDuznika.isValidan() == true))) {
			System.out.println("Medjubankarski transfer");
			medjubankarskiTransfer(analitikaDto, racunDuznika, racunPoverioca);
		} else {
			System.out.println("Greska");
			greska(analitikaDto);
		}

		return null;
	}

	private void uplata(AnalitikaDto analitikaDto, Racun racunPoverioca) {
		DnevnoStanjeRacuna novoDnevnoStanjePoverioca = getNovoDnevnoStanjePoverioca(analitikaDto, racunPoverioca);
		dnevnoStanjeRepository.save(novoDnevnoStanjePoverioca);

		AnalitikaIzvoda analitikaIzvoda = getAnalitikaIzvoda(analitikaDto, novoDnevnoStanjePoverioca, (short) 1);
		analatikaIzvodaRepository.save(analitikaIzvoda);
	}

	private void medjubankarskiTransfer(AnalitikaDto analitikaDto, Racun racunDuznika, Racun racunPoverioca) {
		PravnoLice bankaDuznika = racunDuznika.getBanka();
		PravnoLice bankaPoverioca = racunPoverioca.getBanka();
		
		Racun racunBankeDuznika = racunRepository.findFirstByLiceOrderByIdDesc(bankaDuznika);
		Racun racunBankePoverioca = racunRepository.findFirstByLiceOrderByIdDesc(bankaPoverioca);
				
		if (bankaDuznika.getId() == bankaPoverioca.getId()) {
			//////////////////////////////////////////////////////////////
			// Ako je ista banka, ona ce direktno skinuti sa racuna jednog klijenta i staviti na racun drugog klijenta
			DnevnoStanjeRacuna novoDnevnoStanjeDuznika = getNovoDnevnoStanjeDuznika(analitikaDto, racunDuznika);
			DnevnoStanjeRacuna novoDnevnoStanjePoverioca = getNovoDnevnoStanjePoverioca(analitikaDto, racunPoverioca);
			
			// Sacuvamo to trenutno
			dnevnoStanjeRepository.saveAll(Arrays.asList(
					novoDnevnoStanjeDuznika,
					novoDnevnoStanjePoverioca
				));
			
			AnalitikaIzvoda analitikaIzvodaPrva = getAnalitikaIzvoda(analitikaDto, novoDnevnoStanjeDuznika, (short) 1);
			AnalitikaIzvoda analitikaIzvodaDruga = getAnalitikaIzvoda(analitikaDto, novoDnevnoStanjePoverioca, (short) 1);
			
			analatikaIzvodaRepository.saveAll(Arrays.asList(
					analitikaIzvodaPrva,
					analitikaIzvodaDruga
				));
		} else {
			//////////////////////////////////////////////////////////////
			// Ako su dve razlicite banke
			// Jedna banka skine sa racune druge banke, 
			// nakon toga banka sa koje je skinuto uzima sredstva od klijenta i stavlja na svoj racun.
			// Banka poverioca prvo doda na svoj racun,
			// a zatim prosledju na racun klijenta
			// i skida to sa svog racuna			
			MedjubankarskiNalog medjubankarskiNalog = new MedjubankarskiNalog();

			if (analitikaDto.isHitno() || analitikaDto.getIznos().compareTo(new BigDecimal("250000")) >= 0) {
				medjubankarskiNalog.setTip("rtgs");
			} else {
				medjubankarskiNalog.setTip("klir");
			}
			
			medjubankarskiNalog.setDatum(new Date());
			medjubankarskiNalog.setDatumValute(new Date());
			medjubankarskiNalog.setRacunBankeDuznika(racunBankeDuznika);
			medjubankarskiNalog.setRacunBankePoverioca(racunBankePoverioca);
			medjubankarskiNalog.setUkupanIznos(analitikaDto.getIznos());
			if (analitikaDto.getValutaId() != null)
				medjubankarskiNalog.setValuta(valutaRepository.findById(analitikaDto.getValutaId()).orElse(null));
			
			// Sa jednog racuna banke se doda a sa drugog skine
			DnevnoStanjeRacuna novoDnevnoStanjeBankeDuznika = getNovoDnevnoStanjeDuznika(analitikaDto, racunBankeDuznika);
			DnevnoStanjeRacuna novoDnevnoStanjeBankePoverioca = getNovoDnevnoStanjePoverioca(analitikaDto, racunBankePoverioca);
			
			// Sacuvamo to trenutno
			dnevnoStanjeRepository.saveAll(Arrays.asList(
					novoDnevnoStanjeBankeDuznika,
					novoDnevnoStanjeBankePoverioca
				));
			
			// Nakon toga banke skidaju sa racuna klijenta
			DnevnoStanjeRacuna novoDnevnoStanjeDuznika = getNovoDnevnoStanjeDuznika(analitikaDto, racunDuznika);
			DnevnoStanjeRacuna novoDnevnoStanjePoverioca = getNovoDnevnoStanjePoverioca(analitikaDto, racunPoverioca);
			
			// Sacuvamo to trenutno
			dnevnoStanjeRepository.saveAll(Arrays.asList(
					novoDnevnoStanjeDuznika,
					novoDnevnoStanjePoverioca
				));
			
			// Nakon toga banke vracaju na svoje prvobitno stanje
			DnevnoStanjeRacuna opetPonovnoDnevnoStanjeBankeDuznika = getNovoDnevnoStanjePoverioca(analitikaDto, racunBankeDuznika);
			DnevnoStanjeRacuna opetPonovnoDnevnoStanjeBankePoverioca = getNovoDnevnoStanjeDuznika(analitikaDto, racunBankePoverioca);

			// Sacuvamo to opet
			dnevnoStanjeRepository.saveAll(Arrays.asList(
					opetPonovnoDnevnoStanjeBankeDuznika,
					opetPonovnoDnevnoStanjeBankePoverioca
				));
			
//			medjubankarskiNalogRepository.save(medjubankarskiNalog);
			
			AnalitikaIzvoda analitikaIzvodaDuznika = getAnalitikaIzvoda(analitikaDto, novoDnevnoStanjeDuznika, (short) 1);
			AnalitikaIzvoda analitikaIzvodaPoverioca = getAnalitikaIzvoda(analitikaDto, novoDnevnoStanjePoverioca, (short) 1);

			Set<AnalitikaIzvoda> analitikeIzvoda = new HashSet<>();
			analitikeIzvoda.add(analitikaIzvodaDuznika);
			analitikeIzvoda.add(analitikaIzvodaPoverioca);
			medjubankarskiNalog.setAnalitikeIzvoda(analitikeIzvoda);
			
			// Sacuvamo to opet
			analatikaIzvodaRepository.saveAll(Arrays.asList(
					analitikaIzvodaDuznika,
					analitikaIzvodaPoverioca
				));
			
			medjubankarskiNalogRepository.save(medjubankarskiNalog);
		}
	}

	private void greska(AnalitikaDto analitikaDto) {
		analatikaIzvodaRepository.save(getAnalitikaIzvoda(analitikaDto, null, (short) 8));
	}

	private AnalitikaIzvoda getAnalitikaIzvoda(AnalitikaDto analitikaDto, DnevnoStanjeRacuna novoDnevnoStanjePoverioca,
			short tipGreske) {
		AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
		analitikaIzvoda.setDatumPrijema(new Date());
		analitikaIzvoda.setDatumValute(new Date());
		analitikaIzvoda.setStanjeRacuna(novoDnevnoStanjePoverioca);
		analitikaIzvoda.setDuznikNalogodavac(analitikaDto.getDuznikNalogodavac());
		analitikaIzvoda.setHitno(analitikaDto.isHitno());
		analitikaIzvoda.setIznos(analitikaDto.getIznos());
		if(analitikaDto.getModelOdobrenja()!=null)
			analitikaIzvoda.setModelOdobrenja(analitikaDto.getModelOdobrenja().shortValue());
		if(analitikaDto.getModelZaduzenja()!=null)
			analitikaIzvoda.setModelZaduzenja(analitikaDto.getModelZaduzenja().shortValue());
		if(analitikaDto.getNaseljenoMestoId()!=null)
			analitikaIzvoda.setNaseljenoMesto(naseljenaMestaRepository.getOne(analitikaDto.getNaseljenoMestoId()));
		if(analitikaDto.getValutaId()!=null)
			analitikaIzvoda.setValuta(valutaRepository.getOne(analitikaDto.getValutaId()));
		if(analitikaDto.getVrstaPlacanjaId()!=null)
			analitikaIzvoda.setVrstaPlacanja(vrstaPlacanjaRepository.getOne(analitikaDto.getVrstaPlacanjaId()));
		analitikaIzvoda.setPoverilacPrimalac(analitikaDto.getPoverilacPrimalac());
		analitikaIzvoda.setPozivNaBrojOdobrenja(analitikaDto.getPozivNaBrojOdobrenja());
		analitikaIzvoda.setPozivNaBrojZaduzenja(analitikaDto.getPozivNaBrojZaduzenja());
		analitikaIzvoda.setRacunDuznika(analitikaDto.getRacunDuznika());
		analitikaIzvoda.setRacunPoverioca(analitikaDto.getRacunPoverioca());
		analitikaIzvoda.setStatus("1");
		analitikaIzvoda.setTipGreske(tipGreske);
		analitikaIzvoda.setSvrhaPlacanja(analitikaDto.getSvrhaPlacanja());

		return analitikaIzvoda;
	}

	private DnevnoStanjeRacuna getNovoDnevnoStanjeDuznika(AnalitikaDto analitikaDto, Racun racunDuznika) {
		DnevnoStanjeRacuna dnevnoStanjeDuznika = dnevnoStanjeRepository.findFirstByRacunOrderByIdDesc(racunDuznika);
		if (dnevnoStanjeDuznika==null) {
			System.out.println("Ne ocitava dnevno stanje Duznika");
		}
		System.out.println("Id stanja: "+ dnevnoStanjeDuznika.getId());
		DnevnoStanjeRacuna novoDnevnoStanjeDuznika;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String datumPrometa = sdf.format(dnevnoStanjeDuznika.getDatumPromenta());
		String sadasnjiDatum = sdf.format(new Date());
		
		System.out.println("Datum prometa " + datumPrometa);
		System.out.println("Sadasnji datum " + sadasnjiDatum);
		
		if (datumPrometa.equals(sadasnjiDatum)) {
			novoDnevnoStanjeDuznika = dnevnoStanjeDuznika;
			BigDecimal prometNaTeret = novoDnevnoStanjeDuznika.getPrometNaTeret().add(analitikaDto.getIznos());
			novoDnevnoStanjeDuznika.setPrometNaTeret(prometNaTeret);
		} else {
			novoDnevnoStanjeDuznika = new DnevnoStanjeRacuna();
			novoDnevnoStanjeDuznika.setPrethodnoStanje(dnevnoStanjeDuznika.getNovoStanje());
			novoDnevnoStanjeDuznika.setDatumPromenta(new Date());
			novoDnevnoStanjeDuznika.setPrometNaTeret(analitikaDto.getIznos());
			novoDnevnoStanjeDuznika.setPrometUKorist(new BigDecimal(0));
		}
							
		novoDnevnoStanjeDuznika.setNovoStanje(dnevnoStanjeDuznika.getNovoStanje().subtract(analitikaDto.getIznos()));
		novoDnevnoStanjeDuznika.setRacun(racunDuznika);
		
		return novoDnevnoStanjeDuznika;
	}

	private DnevnoStanjeRacuna getNovoDnevnoStanjePoverioca(AnalitikaDto analitikaDto, Racun racunPoverioca) {
		DnevnoStanjeRacuna dnevnoStanjePoverioca = dnevnoStanjeRepository.findFirstByRacunOrderByIdDesc(racunPoverioca);

		DnevnoStanjeRacuna novoDnevnoStanjePoverioca;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String datumPrometa = sdf.format(dnevnoStanjePoverioca.getDatumPromenta());
		String sadasnjiDatum = sdf.format(new Date());
		
		System.out.println("Datum prometa " + datumPrometa);
		System.out.println("Sadasnji datum" + sadasnjiDatum);
		
		if (datumPrometa.equals(sadasnjiDatum)) {
			novoDnevnoStanjePoverioca = dnevnoStanjePoverioca;
			BigDecimal prometUKorist = novoDnevnoStanjePoverioca.getPrometUKorist().add(analitikaDto.getIznos());		
			novoDnevnoStanjePoverioca.setPrometUKorist(prometUKorist);
		} else {
			novoDnevnoStanjePoverioca = new DnevnoStanjeRacuna();
			novoDnevnoStanjePoverioca.setPrethodnoStanje(dnevnoStanjePoverioca.getNovoStanje());
			novoDnevnoStanjePoverioca.setDatumPromenta(new Date());	
			novoDnevnoStanjePoverioca.setPrometUKorist(analitikaDto.getIznos());
			novoDnevnoStanjePoverioca.setPrometNaTeret(new BigDecimal(0));
		}
					
		novoDnevnoStanjePoverioca.setNovoStanje(dnevnoStanjePoverioca.getNovoStanje().add(analitikaDto.getIznos()));
		novoDnevnoStanjePoverioca.setRacun(racunPoverioca);
		
		return novoDnevnoStanjePoverioca;
	}

}
