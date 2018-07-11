package rs.ftn.poslovna.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.Drzava;
import rs.ftn.poslovna.domain.FizickoLice;
import rs.ftn.poslovna.domain.NaseljenoMesto;
import rs.ftn.poslovna.dto.FizickoLiceDto;
import rs.ftn.poslovna.repository.DrzavaRepository;
import rs.ftn.poslovna.repository.FizickoLiceRepository;
import rs.ftn.poslovna.repository.NaseljenaMestaRepository;
import rs.ftn.poslovna.service.FizickoLiceService;

@Service
public class FizickoLiceServiceImpl implements FizickoLiceService{
	
	@Autowired
	private FizickoLiceRepository fizliceRepository;
	
	@Autowired
	private DrzavaRepository drzavaRepository;
	
	@Autowired
	private NaseljenaMestaRepository nasmestoRepository;

	@Override
	public FizickoLiceDto addFizickoLice(FizickoLiceDto fizlicedto) {
		Drzava drzava = drzavaRepository.getOne(fizlicedto.getDrzavaId());
		NaseljenoMesto nmesto = nasmestoRepository.getOne(fizlicedto.getNaseljenomestoId());
		
		if(drzava == null || nmesto == null) {
			return null;
		}
		
		FizickoLice fizlice = fizliceRepository.save(new FizickoLice(fizlicedto.getAdresa(),nmesto,fizlicedto.getIme(),
				fizlicedto.getPrezime(),fizlicedto.getJmbg(),fizlicedto.getBrojLicneKarte(),drzava));
		
		fizlicedto.setId(fizlice.getId());
		
		return fizlicedto;
	}

	@Override
	public List<FizickoLiceDto> getAllFizickoLice() {
		return fizliceRepository.findAll().stream().map(FizickoLiceDto::new).collect(Collectors.toList());
	}

}
