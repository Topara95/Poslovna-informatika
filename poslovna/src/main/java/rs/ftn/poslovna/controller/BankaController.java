package rs.ftn.poslovna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ftn.poslovna.domain.Banka;
import rs.ftn.poslovna.service.BankaService;

@RestController
@RequestMapping("/api/banks")
public class BankaController {
	
	@Autowired
	private BankaService bankaService;
	
	@PostMapping
	public ResponseEntity<Banka> addBanka(@RequestBody Banka banka){
		Banka temp = bankaService.addBanka(banka);
		if(temp!=null) {
			return new ResponseEntity<>(temp,HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}

}
