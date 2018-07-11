package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.KursnaListaDto;

public interface KursnaListaService {

	List<KursnaListaDto> getAll();

	KursnaListaDto add(KursnaListaDto kursnaListaDto);

}
