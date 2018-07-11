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

import rs.ftn.poslovna.dto.RacunDto;
import rs.ftn.poslovna.service.RacunService;

@RestController
@RequestMapping("api/racuni")
public class RacunController {
	
	@Autowired
	private RacunService racunService;
	
	
	@PostMapping
	public ResponseEntity<?> addRacun(@RequestBody @Valid RacunDto racunDto){
		racunDto = racunService.addRacun(racunDto);
		
		if(racunDto == null) {
			return new ResponseEntity<>("Greska prilikom kreiranja racuna", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(racunDto,HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<RacunDto> getRacuni(){
		return racunService.getRacuni();
	}

}
