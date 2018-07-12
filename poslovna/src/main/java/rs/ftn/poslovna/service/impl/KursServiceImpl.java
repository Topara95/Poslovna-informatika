package rs.ftn.poslovna.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.Kurs;
import rs.ftn.poslovna.domain.KursnaLista;
import rs.ftn.poslovna.domain.Valuta;
import rs.ftn.poslovna.dto.KursDto;
import rs.ftn.poslovna.repository.KursRepository;
import rs.ftn.poslovna.repository.KursnaListaRepository;
import rs.ftn.poslovna.repository.ValutaRepository;
import rs.ftn.poslovna.service.KursService;

@Service
public class KursServiceImpl implements KursService {

	@Autowired
	private KursRepository kursRepository;
	
	@Autowired
	private ValutaRepository valutaRepository;
	
	@Autowired
	private KursnaListaRepository kursnaListaRepository;
	
	
	@Override
	public List<KursDto> getAll() {
		return kursRepository.findAll().stream().map(KursDto::new).collect(Collectors.toList());
	}

	@Override
	public KursDto add(KursDto kursDto) {
		
		KursnaLista kl = kursnaListaRepository.findById(kursDto.getSifraKursneListe()).get();
		
		Valuta osnovna = valutaRepository.findById(kursDto.getSifraOsnovneValute()).get();
		
		Valuta prema = valutaRepository.findById(kursDto.getSifraPremaValuti()).get();
		
		if(kursRepository.existsById(kursDto.getId())) {
			return null;
		}
		
		if(kl == null || osnovna== null || prema==null) {
			return null;
		}
		
		
		Kurs kurs = kursRepository.save(new Kurs(kursDto.getId(), kursDto.getKupovni(),kursDto.getKupovni().add(kursDto.getProdajni()).divide(new BigDecimal(2)),kursDto.getProdajni(),kl,osnovna,prema));
		return new KursDto(kurs);
	}

}
