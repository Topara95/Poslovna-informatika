package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.VrstaPlacanjaDto;

public interface VrstaPlacanjaService {

	public List<VrstaPlacanjaDto> getAll();
	public VrstaPlacanjaDto add(VrstaPlacanjaDto vrstaPlacanjaDto);
	
}
