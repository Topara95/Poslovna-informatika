package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.DrzavaDto;

public interface DrzavaService {

	List<DrzavaDto> getAll();

	DrzavaDto addDrzava(DrzavaDto drzava);

}
