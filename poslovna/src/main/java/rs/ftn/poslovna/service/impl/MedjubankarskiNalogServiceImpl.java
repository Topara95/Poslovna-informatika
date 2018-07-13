package rs.ftn.poslovna.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.dto.MedjubankarskiNalogDto;
import rs.ftn.poslovna.repository.MedjubankarskiNalogRepository;
import rs.ftn.poslovna.service.MedjubankarskiNalogService;

@Service
public class MedjubankarskiNalogServiceImpl implements MedjubankarskiNalogService{
	
	@Autowired
	private MedjubankarskiNalogRepository mnRepository;

	@Override
	public List<MedjubankarskiNalogDto> getNalozi() {
		return mnRepository.findAll().stream().map(MedjubankarskiNalogDto::new).collect(Collectors.toList());
	}

	@Override
	public MedjubankarskiNalogDto getOne(int id) {
		return new MedjubankarskiNalogDto(mnRepository.findById(id).get());
	}

}
