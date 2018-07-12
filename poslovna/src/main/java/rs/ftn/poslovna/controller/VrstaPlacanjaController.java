package rs.ftn.poslovna.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ftn.poslovna.dto.VrstaPlacanjaDto;
import rs.ftn.poslovna.service.VrstaPlacanjaService;

@RestController
@RequestMapping("api/vrstaPlacanja")
public class VrstaPlacanjaController {
	
	@Autowired
	private VrstaPlacanjaService vrstaPlacanjaService;
	
	@GetMapping
	public List<VrstaPlacanjaDto> get(){
		return vrstaPlacanjaService.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> post(@RequestBody @Valid VrstaPlacanjaDto drzava) {
		VrstaPlacanjaDto added = vrstaPlacanjaService.add(drzava);

		if (added == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(added);
		}
	}
}
