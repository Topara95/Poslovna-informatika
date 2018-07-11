package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.RacunDto;

public interface RacunService {
	
	RacunDto addRacun(RacunDto racunDto);
	
	List<RacunDto> getRacuni();
	
}
