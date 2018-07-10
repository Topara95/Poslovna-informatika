package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.AtributiBankeDto;

public interface AtributiBankeService {
	
	AtributiBankeDto addAtributiBanke(AtributiBankeDto ab);
	
	List<AtributiBankeDto> getAllAtributiBanke();
	
	AtributiBankeDto getOne(String atributiId);
	
}
