package rs.ftn.poslovna.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.AtributiBanke;
import rs.ftn.poslovna.dto.AtributiBankeDto;
import rs.ftn.poslovna.repository.AtributiBankeRepository;
import rs.ftn.poslovna.service.AtributiBankeService;

@Service
public class AtributiBankeServiceImpl implements AtributiBankeService{
	
	@Autowired
	private AtributiBankeRepository abrepository;

	@Override
	public AtributiBankeDto addAtributiBanke(AtributiBankeDto ab) {
		if(abrepository.existsById(ab.getId())) {
			return null;
		}
		
		abrepository.save(new AtributiBanke(ab.getId(),ab.getSwiftKod()));
		return ab;
	}

	@Override
	public List<AtributiBankeDto> getAllAtributiBanke() {
		return abrepository.findAll().stream().map(AtributiBankeDto::new).collect(Collectors.toList());
	}

	@Override
	public AtributiBankeDto getOne(String atributiId) {
		AtributiBanke ab = abrepository.getOne(atributiId);
		if(ab != null) {
			return new AtributiBankeDto(ab);
		}
		return null;
	}

}
