package rs.ftn.poslovna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ftn.poslovna.dto.MedjubankarskiNalogDto;
import rs.ftn.poslovna.service.MedjubankarskiNalogService;

@RestController
@RequestMapping("api/medjubankarskiNalozi")
public class MedjubankarskiNalogController {
	
	@Autowired
	private MedjubankarskiNalogService mnService;
	
	@GetMapping
	private List<MedjubankarskiNalogDto> getNalozi(){
		return mnService.getNalozi();
	}

}
