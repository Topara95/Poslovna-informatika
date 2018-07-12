package rs.ftn.poslovna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ftn.poslovna.dto.DnevnoStanjeRacunaDto;
import rs.ftn.poslovna.service.DnevnoStanjeRacunaService;

@RestController
@RequestMapping("api/dnevnaStanjaRacuna")
public class DnevnoStanjeRacunaController {
	
	@Autowired
	private DnevnoStanjeRacunaService dsrService;
	
	@GetMapping
	public List<DnevnoStanjeRacunaDto> getDnevnaStanja(){
		return dsrService.getAllStanja();
	}
	
}
