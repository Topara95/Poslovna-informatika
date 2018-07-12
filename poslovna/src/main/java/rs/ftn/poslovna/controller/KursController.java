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

import rs.ftn.poslovna.dto.KursDto;
import rs.ftn.poslovna.service.KursService;

@RestController
@RequestMapping("api/kursevi")
public class KursController {
	
	@Autowired
	private KursService kursService;
	
	@GetMapping
	public List<KursDto> get(){
		return kursService.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> post(@RequestBody @Valid KursDto kursDto) {
		KursDto added = kursService.add(kursDto);

		if (added == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(added);
		}
	}

}
