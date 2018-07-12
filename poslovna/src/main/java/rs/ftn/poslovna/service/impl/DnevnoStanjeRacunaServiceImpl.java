package rs.ftn.poslovna.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.dto.DnevnoStanjeRacunaDto;
import rs.ftn.poslovna.repository.DnevnoStanjeRepository;
import rs.ftn.poslovna.service.DnevnoStanjeRacunaService;

@Service
public class DnevnoStanjeRacunaServiceImpl implements DnevnoStanjeRacunaService{
	
	@Autowired
	private DnevnoStanjeRepository dsrRepository;

	@Override
	public List<DnevnoStanjeRacunaDto> getAllStanja() {
		return dsrRepository.findAll().stream().map(DnevnoStanjeRacunaDto::new).collect(Collectors.toList());
	}

}
