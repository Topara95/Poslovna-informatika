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

import rs.ftn.poslovna.dto.KursnaListaDto;
import rs.ftn.poslovna.service.KursnaListaService;

@RestController
@RequestMapping("api/kursneListe")
public class KursnaListaController {

	@Autowired
	private KursnaListaService kursnaListaService;
	
	@GetMapping
	public List<KursnaListaDto> get() {
		return kursnaListaService.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> post(@RequestBody @Valid KursnaListaDto kursnaListaDto) {
		KursnaListaDto added = kursnaListaService.add(kursnaListaDto);

		if (added == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(added);
		}
	}
	
}
