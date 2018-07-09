package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.ValutaDto;

public interface ValutaService {

	List<ValutaDto> getAll();

	ValutaDto add(ValutaDto valuta);

}
