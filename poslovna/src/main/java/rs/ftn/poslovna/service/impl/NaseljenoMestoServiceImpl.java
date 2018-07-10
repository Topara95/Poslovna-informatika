package rs.ftn.poslovna.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.Drzava;
import rs.ftn.poslovna.domain.NaseljenoMesto;
import rs.ftn.poslovna.dto.NaseljenoMestoDto;
import rs.ftn.poslovna.repository.DrzavaRepository;
import rs.ftn.poslovna.repository.NaseljenaMestaRepository;
import rs.ftn.poslovna.service.NaseljenoMestoService;

@Service
public class NaseljenoMestoServiceImpl implements NaseljenoMestoService {

	@Autowired
	private NaseljenaMestaRepository naseljenaMestaRepository;

	@Autowired
	private DrzavaRepository drzavaRepository;

	@Override
	public List<NaseljenoMestoDto> getAll() {
		return naseljenaMestaRepository.findAll().stream().map(NaseljenoMestoDto::new).collect(Collectors.toList());
	}

	@Override
	public NaseljenoMestoDto add(NaseljenoMestoDto naseljenoMestoDto) {
		Drzava drzava = drzavaRepository.findById(naseljenoMestoDto.getDrzavaId()).orElse(null);

		if (drzava == null) {
			return null;
		}

		NaseljenoMesto naseljenoMesto = naseljenaMestaRepository
				.save(new NaseljenoMesto(naseljenoMestoDto.getNaziv(), naseljenoMestoDto.getPtt(), drzava));

		naseljenoMestoDto.setId(naseljenoMesto.getId());

		return naseljenoMestoDto;
	}

}
