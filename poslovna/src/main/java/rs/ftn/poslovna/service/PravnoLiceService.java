package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.LiceDto;
import rs.ftn.poslovna.dto.PravnoLiceDto;

public interface PravnoLiceService {
	
	PravnoLiceDto addPravnoLice(PravnoLiceDto pldto);
	
	List<PravnoLiceDto> getPravnaLica();
	
	List<PravnoLiceDto> getBanke();
	
	List<LiceDto> getLica();
	
}
