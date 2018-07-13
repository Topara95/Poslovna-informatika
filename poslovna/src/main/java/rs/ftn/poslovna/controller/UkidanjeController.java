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

import rs.ftn.poslovna.dto.UkidanjeDto;
import rs.ftn.poslovna.service.UkidanjeService;

@RestController
@RequestMapping("api/ukidanje")
public class UkidanjeController {

	@Autowired
	private UkidanjeService ukidanjeService;
	
	@PostMapping
	public ResponseEntity<?> ukiniRacun(@RequestBody @Valid UkidanjeDto ukidanjeDto){
		
		ukidanjeDto = ukidanjeService.ukiniRacun(ukidanjeDto);
		
		if(ukidanjeDto == null) {
			return new ResponseEntity<>("Greska prilikom ukidanja racuna!", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(ukidanjeDto, HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public List<UkidanjeDto> getUkidanja(){
		return ukidanjeService.getUkidanja();
	}
	
}
