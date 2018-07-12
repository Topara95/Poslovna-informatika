package rs.ftn.poslovna.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.VrstaPlacanja;
import rs.ftn.poslovna.dto.VrstaPlacanjaDto;
import rs.ftn.poslovna.repository.VrstaPlacanjaRepository;
import rs.ftn.poslovna.service.VrstaPlacanjaService;

@Service
public class VrstaPlacanjaServiceImpl implements VrstaPlacanjaService {

	@Autowired
	private VrstaPlacanjaRepository vrstaPlacanjaRepository;
	
	@Override
	public List<VrstaPlacanjaDto> getAll() {
		return vrstaPlacanjaRepository.findAll().stream().map(VrstaPlacanjaDto::new).collect(Collectors.toList());
	}

	@Override
	public VrstaPlacanjaDto add(VrstaPlacanjaDto vrstaPlacanjaDto) {
		// TODO Auto-generated method stub
		if(vrstaPlacanjaRepository.existsById(vrstaPlacanjaDto.getId())) {
			return null;
		}
		
		VrstaPlacanja vp = vrstaPlacanjaRepository.save(new VrstaPlacanja(vrstaPlacanjaDto));
		
		return new VrstaPlacanjaDto(vp);
		
	}

}
