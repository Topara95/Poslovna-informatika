package rs.ftn.poslovna.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.DnevnoStanjeRacuna;
import rs.ftn.poslovna.domain.Lice;
import rs.ftn.poslovna.domain.PravnoLice;
import rs.ftn.poslovna.domain.Racun;
import rs.ftn.poslovna.domain.Valuta;
import rs.ftn.poslovna.dto.RacunDto;
import rs.ftn.poslovna.repository.DnevnoStanjeRepository;
import rs.ftn.poslovna.repository.LiceRepository;
import rs.ftn.poslovna.repository.PravnoLiceRepository;
import rs.ftn.poslovna.repository.RacunRepository;
import rs.ftn.poslovna.repository.ValutaRepository;
import rs.ftn.poslovna.service.RacunService;

@Service
public class RacunServiceImpl implements RacunService {

	@Autowired
	private RacunRepository racunRepository;

	@Autowired
	private ValutaRepository valutaRepository;

	@Autowired
	private PravnoLiceRepository plRepository;

	@Autowired
	private LiceRepository liceRepository;
	
	@Autowired
	private DnevnoStanjeRepository dnevnoStanjeRepository;

	@Override
	public RacunDto addRacun(RacunDto racunDto) {
		Valuta valuta = valutaRepository.getOne(racunDto.getValutaId());
		PravnoLice banka = plRepository.getOne(racunDto.getBankaId());
		Lice lice = liceRepository.getOne(racunDto.getLiceId());

		if (banka == null || valuta == null || lice == null) {
			return null;
		}

		Racun racun = racunRepository.save(new Racun(racunDto.getBrojRacuna(), new Date(), true, lice, banka, valuta));
		
		DnevnoStanjeRacuna dnevnoStanjeRacuna = new DnevnoStanjeRacuna();
		dnevnoStanjeRacuna.setRacun(racun);
		dnevnoStanjeRacuna.setDatumPromenta(new Date());
		dnevnoStanjeRacuna.setNovoStanje(new BigDecimal(0));
		dnevnoStanjeRacuna.setPrethodnoStanje(new BigDecimal(0));
		dnevnoStanjeRacuna.setPrometNaTeret(new BigDecimal(0));
		dnevnoStanjeRacuna.setPrometUKorist(new BigDecimal(0));
		
		dnevnoStanjeRepository.save(dnevnoStanjeRacuna);
		
		return new RacunDto(racun);
	}

	@Override
	public List<RacunDto> getRacuni() {
		return racunRepository.findAll().stream().map(RacunDto::new).collect(Collectors.toList());
	}

}
