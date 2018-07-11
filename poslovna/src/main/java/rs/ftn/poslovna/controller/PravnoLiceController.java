package rs.ftn.poslovna.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ftn.poslovna.dto.LiceDto;
import rs.ftn.poslovna.dto.PravnoLiceDto;
import rs.ftn.poslovna.service.PravnoLiceService;

@RestController
@RequestMapping("api/lica/pravnalica")
public class PravnoLiceController {
	
	@Autowired
	private PravnoLiceService plService;
	
	@PostMapping
	private ResponseEntity<?> addPravnoLice(@RequestBody @Valid PravnoLiceDto pldto){
		 pldto = plService.addPravnoLice(pldto);
		 
		 if(pldto == null) {
			 return new ResponseEntity<>("Greska prilikom kreiranja pravnog lica", HttpStatus.BAD_REQUEST);
		 }
		 
		 return new ResponseEntity<>(pldto,HttpStatus.CREATED);
	}
	
	@GetMapping
	private List<PravnoLiceDto> getPravnaLica(){
		return plService.getPravnaLica();
	}
	
	@GetMapping("/banke")
	private List<PravnoLiceDto> getBanke(){
		return plService.getBanke();
	}
	
	@GetMapping("/lica")
	private List<LiceDto> getLica(){
		return plService.getLica();
	}

}
