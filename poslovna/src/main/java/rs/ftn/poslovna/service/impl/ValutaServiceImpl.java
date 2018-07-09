package rs.ftn.poslovna.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.Drzava;
import rs.ftn.poslovna.domain.Valuta;
import rs.ftn.poslovna.dto.ValutaDto;
import rs.ftn.poslovna.repository.DrzavaRepository;
import rs.ftn.poslovna.repository.ValutaRepository;
import rs.ftn.poslovna.service.ValutaService;

@Service
public class ValutaServiceImpl implements ValutaService {

	@Autowired
	private ValutaRepository valutaRepository;

	@Autowired
	private DrzavaRepository drzavaRepository;

	@Override
	public List<ValutaDto> getAll() {
		return valutaRepository.findAll().stream().map(ValutaDto::new).collect(Collectors.toList());
	}

	@Override
	public ValutaDto add(ValutaDto valutaDto) {
		Drzava drzava = drzavaRepository.findById(valutaDto.getDrzavaId()).orElse(null);

		if (drzava == null) {
			return null;
		}

		Valuta valuta = valutaRepository
				.save(new Valuta(valutaDto.getZvanicnaSifra(), valutaDto.getNaziv(), valutaDto.isDomicilna(), drzava));

		valutaDto.setId(valuta.getId());

		return valutaDto;
	}

}
