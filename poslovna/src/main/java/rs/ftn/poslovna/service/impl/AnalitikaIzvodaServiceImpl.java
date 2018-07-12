package rs.ftn.poslovna.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

		if (racunPoverioca != null && racunDuznika == null) {
			uplata(analitikaDto, racunPoverioca);
		} else if (racunPoverioca != null && racunDuznika != null) {
			medjubankarskiTransfer(analitikaDto, racunDuznika, racunPoverioca);
		} else {
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
			medjubankarskiNalog.setValuta(valutaRepository.getOne(analitikaDto.getValutaId()));
			
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
			
			medjubankarskiNalogRepository.save(medjubankarskiNalog);
			
			AnalitikaIzvoda analitikaIzvodaDuznika = getAnalitikaIzvoda(analitikaDto, novoDnevnoStanjeDuznika, (short) 1);
			AnalitikaIzvoda analitikaIzvodaPoverioca = getAnalitikaIzvoda(analitikaDto, novoDnevnoStanjePoverioca, (short) 1);
			
			// Sacuvamo to opet
			analatikaIzvodaRepository.saveAll(Arrays.asList(
					analitikaIzvodaDuznika,
					analitikaIzvodaPoverioca
				));
		}
	}

	private void greska(AnalitikaDto analitikaDto) {
		analatikaIzvodaRepository.save(getAnalitikaIzvoda(analitikaDto, null, (short) 1));
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
		analitikaIzvoda.setModelOdobrenja(analitikaDto.getModelOdobrenja().shortValue());
		analitikaIzvoda.setModelZaduzenja(analitikaDto.getModelZaduzenja().shortValue());
		analitikaIzvoda.setNaseljenoMesto(naseljenaMestaRepository.getOne(analitikaDto.getNaseljenoMestoId()));
		analitikaIzvoda.setValuta(valutaRepository.getOne(analitikaDto.getValutaId()));
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

		DnevnoStanjeRacuna novoDnevnoStanjeDuznika = new DnevnoStanjeRacuna();
		novoDnevnoStanjeDuznika.setDatumPromenta(new Date());
		novoDnevnoStanjeDuznika.setPrethodnoStanje(dnevnoStanjeDuznika.getNovoStanje());
		novoDnevnoStanjeDuznika.setPrometNaTeret(analitikaDto.getIznos());
		novoDnevnoStanjeDuznika.setPrometUKorist(new BigDecimal(0));
		novoDnevnoStanjeDuznika.setNovoStanje(dnevnoStanjeDuznika.getNovoStanje().subtract(analitikaDto.getIznos()));

		return novoDnevnoStanjeDuznika;
	}

	private DnevnoStanjeRacuna getNovoDnevnoStanjePoverioca(AnalitikaDto analitikaDto, Racun racunPoverioca) {
		DnevnoStanjeRacuna dnevnoStanjePoverioca = dnevnoStanjeRepository.findFirstByRacunOrderByIdDesc(racunPoverioca);

		DnevnoStanjeRacuna novoDnevnoStanjePoverioca = new DnevnoStanjeRacuna();
		novoDnevnoStanjePoverioca.setDatumPromenta(new Date());
		novoDnevnoStanjePoverioca.setPrethodnoStanje(dnevnoStanjePoverioca.getNovoStanje());
		novoDnevnoStanjePoverioca.setPrometUKorist(analitikaDto.getIznos());
		novoDnevnoStanjePoverioca.setPrometNaTeret(new BigDecimal(0));
		novoDnevnoStanjePoverioca.setNovoStanje(dnevnoStanjePoverioca.getNovoStanje().add(analitikaDto.getIznos()));

		return dnevnoStanjePoverioca;
	}

}
