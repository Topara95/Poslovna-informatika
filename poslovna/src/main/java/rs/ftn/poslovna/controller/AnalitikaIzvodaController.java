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

import rs.ftn.poslovna.dto.AnalitikaDto;
import rs.ftn.poslovna.service.AnalitikaIzvodaService;

@RestController
@RequestMapping("api/analitika")
public class AnalitikaIzvodaController {

	@Autowired
	private AnalitikaIzvodaService analitikaIzvodaService;

	@GetMapping
	public List<AnalitikaDto> get() {
		return analitikaIzvodaService.getAll();
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody @Valid AnalitikaDto analitikaDto) {
		AnalitikaDto added = analitikaIzvodaService.add(analitikaDto);

		if (added == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(added);
		}
	}
}
