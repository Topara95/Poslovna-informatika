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

import rs.ftn.poslovna.dto.NaseljenoMestoDto;
import rs.ftn.poslovna.service.NaseljenoMestoService;

@RestController
@RequestMapping("api/naseljenaMesta")
public class NaseljenaMestaController {

	@Autowired
	private NaseljenoMestoService naseljenoMestoService;

	@GetMapping
	public List<NaseljenoMestoDto> get() {
		return naseljenoMestoService.getAll();
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody @Valid NaseljenoMestoDto naseljenoMesto) {
		NaseljenoMestoDto added = naseljenoMestoService.add(naseljenoMesto);

		if (added == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(added);
		}
	}

}
