package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.AnalitikaDto;

public interface AnalitikaIzvodaService {

	List<AnalitikaDto> getAll();

	AnalitikaDto add(AnalitikaDto analitikaDto);

}
