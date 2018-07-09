package rs.ftn.poslovna.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.Drzava;
import rs.ftn.poslovna.dto.DrzavaDto;
import rs.ftn.poslovna.repository.DrzavaRepository;
import rs.ftn.poslovna.service.DrzavaService;

@Service
public class DrzavaServiceImpl implements DrzavaService {

	@Autowired
	private DrzavaRepository drzavaRepository;

	@Override
	public DrzavaDto addDrzava(DrzavaDto drzavaDto) {
		if (drzavaRepository.existsById(drzavaDto.getId())) {
			return null;
		}

		drzavaRepository.save(new Drzava(drzavaDto.getId(), drzavaDto.getNazivDrzave()));

		return drzavaDto;
	}

	@Override
	public List<DrzavaDto> getAll() {
		return drzavaRepository.findAll().stream().map(DrzavaDto::new).collect(Collectors.toList());
	}

}
