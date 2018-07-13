package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.UkidanjeDto;

public interface UkidanjeService {
	
	UkidanjeDto ukiniRacun(UkidanjeDto ukidanjeDto);
	
	List<UkidanjeDto> getUkidanja();
	
}
