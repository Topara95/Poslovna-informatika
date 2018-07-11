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

import rs.ftn.poslovna.dto.FizickoLiceDto;
import rs.ftn.poslovna.service.FizickoLiceService;

@RestController
@RequestMapping("api/lica/fizickalica")
public class FizickoLiceController {
	
	@Autowired
	private FizickoLiceService fizliceService;
	
	@PostMapping
	private ResponseEntity<?> addFizickoLice(@RequestBody @Valid FizickoLiceDto fizliceDto){
		FizickoLiceDto dodato = fizliceService.addFizickoLice(fizliceDto);
		
		if(dodato == null) {
			return new ResponseEntity<>("Greska prilikom dodavanja fizickog lica", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(dodato, HttpStatus.CREATED);
	}
	
	@GetMapping
	private List<FizickoLiceDto> getAllFizickoLice(){
		return fizliceService.getAllFizickoLice();
	}
	
}
