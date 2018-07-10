package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.NaseljenoMestoDto;

public interface NaseljenoMestoService {
	
	List<NaseljenoMestoDto> getAll();
	
	NaseljenoMestoDto add(NaseljenoMestoDto naseljenoMesto);
	
}
