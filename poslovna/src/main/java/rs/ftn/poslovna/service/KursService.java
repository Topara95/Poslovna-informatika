package rs.ftn.poslovna.service;

import java.util.List;

import rs.ftn.poslovna.dto.KursDto;

public interface KursService {
	List<KursDto> getAll();
	KursDto add(KursDto kursDto);
}
