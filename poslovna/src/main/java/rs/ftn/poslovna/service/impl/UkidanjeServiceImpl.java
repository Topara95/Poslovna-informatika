package rs.ftn.poslovna.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.DnevnoStanjeRacuna;
import rs.ftn.poslovna.domain.Racun;
import rs.ftn.poslovna.domain.Ukidanje;
import rs.ftn.poslovna.dto.UkidanjeDto;
import rs.ftn.poslovna.repository.DnevnoStanjeRepository;
import rs.ftn.poslovna.repository.RacunRepository;
import rs.ftn.poslovna.repository.UkidanjeRepository;
import rs.ftn.poslovna.service.UkidanjeService;

@Service
public class UkidanjeServiceImpl implements UkidanjeService{
	
	@Autowired
	private UkidanjeRepository ukidanjeRepository;
	
	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired
	private DnevnoStanjeRepository dsrRepository;
	

	@Override
	public UkidanjeDto ukiniRacun(UkidanjeDto ukidanjeDto) {
		Racun zaUkidanje = racunRepository.getOne(ukidanjeDto.getRacunId());
		Racun zaPrebacivanje = racunRepository.findByBrojRacuna(ukidanjeDto.getSredstvaSePrenoseNaRacun());
		
		if(zaUkidanje == null || zaPrebacivanje == null || zaUkidanje.isValidan() == false || zaPrebacivanje.isValidan() == false) {
			return null;
		}
		
		DnevnoStanjeRacuna stanjeUkidanje = dsrRepository.findFirstByRacunOrderByIdDesc(zaUkidanje);
		DnevnoStanjeRacuna stanjePrebacivanje = dsrRepository.findFirstByRacunOrderByIdDesc(zaPrebacivanje);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String datumPrometaPrebacivanje = sdf.format(stanjePrebacivanje.getDatumPromenta());
		String sadasnjiDatum = sdf.format(new Date());
		
		if (datumPrometaPrebacivanje.equals(sadasnjiDatum)) {
			stanjePrebacivanje.setPrometUKorist(stanjePrebacivanje.getPrometUKorist().add(stanjeUkidanje.getNovoStanje()));
			stanjePrebacivanje.setNovoStanje(stanjePrebacivanje.getNovoStanje().add(stanjeUkidanje.getNovoStanje()));
			
			dsrRepository.save(stanjePrebacivanje);
		}else {
			DnevnoStanjeRacuna novoStanjePrebacivanje = new DnevnoStanjeRacuna();
			novoStanjePrebacivanje.setDatumPromenta(new Date());
			novoStanjePrebacivanje.setPrethodnoStanje(stanjePrebacivanje.getNovoStanje());
			novoStanjePrebacivanje.setPrometNaTeret(new BigDecimal(0));
			novoStanjePrebacivanje.setPrometUKorist(stanjeUkidanje.getNovoStanje());
			novoStanjePrebacivanje.setNovoStanje(stanjePrebacivanje.getNovoStanje().add(stanjeUkidanje.getNovoStanje()));
			
			dsrRepository.save(novoStanjePrebacivanje);
		}
		
		zaUkidanje.setValidan(false);
		
		racunRepository.save(zaUkidanje);
		racunRepository.save(zaPrebacivanje);
		
		Ukidanje ukidanje = new Ukidanje(ukidanjeDto.getSredstvaSePrenoseNaRacun(),racunRepository.getOne(ukidanjeDto.getRacunId()));
		ukidanjeDto.setId(ukidanje.getId());
		ukidanjeDto.setDatumUkidanja(ukidanje.getDatumUkidanja());
		ukidanjeRepository.save(ukidanje);
		
		
		return ukidanjeDto;
	}

	@Override
	public List<UkidanjeDto> getUkidanja() {
		return ukidanjeRepository.findAll().stream().map(UkidanjeDto::new).collect(Collectors.toList());
	}

}
