package rs.ftn.poslovna.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.KursnaLista;
import rs.ftn.poslovna.domain.PravnoLice;
import rs.ftn.poslovna.dto.KursnaListaDto;
import rs.ftn.poslovna.repository.KursnaListaRepository;
import rs.ftn.poslovna.repository.PravnoLiceRepository;
import rs.ftn.poslovna.service.KursnaListaService;

@Service
public class KursnaListaServiceImpl implements KursnaListaService {

	@Autowired
	private KursnaListaRepository kursnaListaRepository;

	@Autowired
	private PravnoLiceRepository pravnoLiceRepository;

	@Override
	public List<KursnaListaDto> getAll() {
		return kursnaListaRepository.findAll().stream().map(KursnaListaDto::new).collect(Collectors.toList());
	}

	@Override
	public KursnaListaDto add(KursnaListaDto kursnaListaDto) {
		PravnoLice banka = pravnoLiceRepository.findBanka(kursnaListaDto.getSifraBanke());

		if (banka == null) {
			return null;
		}

		KursnaLista kursnaLista = kursnaListaRepository
				.save(new KursnaLista(kursnaListaDto.getBrojKursneListe(), kursnaListaDto.getDatumVazenja(), banka));

		return new KursnaListaDto(kursnaLista);
	}

}
