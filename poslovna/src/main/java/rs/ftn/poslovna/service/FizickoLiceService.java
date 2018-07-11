package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.FizickoLiceDto;

public interface FizickoLiceService {

	FizickoLiceDto addFizickoLice(FizickoLiceDto fizlicedto);
	
	List<FizickoLiceDto> getAllFizickoLice();
	
}
