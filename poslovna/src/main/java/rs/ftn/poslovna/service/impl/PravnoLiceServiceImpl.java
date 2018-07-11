package rs.ftn.poslovna.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.AtributiBanke;
import rs.ftn.poslovna.domain.NaseljenoMesto;
import rs.ftn.poslovna.domain.PravnoLice;
import rs.ftn.poslovna.dto.LiceDto;
import rs.ftn.poslovna.dto.PravnoLiceDto;
import rs.ftn.poslovna.repository.AtributiBankeRepository;
import rs.ftn.poslovna.repository.LiceRepository;
import rs.ftn.poslovna.repository.NaseljenaMestaRepository;
import rs.ftn.poslovna.repository.PravnoLiceRepository;
import rs.ftn.poslovna.service.PravnoLiceService;

@Service
public class PravnoLiceServiceImpl implements PravnoLiceService{
	
	@Autowired
	private PravnoLiceRepository plRepository;
	
	@Autowired
	private NaseljenaMestaRepository nmRepository;
	
	@Autowired
	private AtributiBankeRepository abRepository;
	
	@Autowired
	private LiceRepository liceRepository;

	@Override
	public PravnoLiceDto addPravnoLice(PravnoLiceDto pldto) {
		NaseljenoMesto nm = nmRepository.getOne(pldto.getNaseljenomestoId());
		if(nm == null) {
			return null;
		}
		
		AtributiBanke ab = null;
		if(pldto.getAtributiBankeId() != null) {
			ab = abRepository.getOne(pldto.getAtributiBankeId());
			if(ab == null) {
				return null;
			}
		}
		
		PravnoLice pl =plRepository.save(new PravnoLice(pldto.getAdresa(),nm,pldto.getNaziv(),pldto.getMaticniBroj(),
				pldto.getPib(),pldto.getEmail(),pldto.getWeb(),pldto.getTelefon(),pldto.getFaks(),
				ab));
		
		pldto.setId(pl.getId());
		
		return pldto;
	}

	@Override
	public List<PravnoLiceDto> getPravnaLica() {
		return plRepository.findAll().stream().map(PravnoLiceDto::new).collect(Collectors.toList());
	}

	@Override
	public List<PravnoLiceDto> getBanke() {
		return plRepository.findAll().stream()
				.filter(p ->  p.getAtributiBanke() != null)
				.map(PravnoLiceDto::new)
				.collect(Collectors.toList());
	}

	@Override
	public List<LiceDto> getLica() {
		return liceRepository.findAll().stream().map(LiceDto::new).collect(Collectors.toList());
	}

}
