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

import rs.ftn.poslovna.dto.ValutaDto;
import rs.ftn.poslovna.service.ValutaService;

@RestController
@RequestMapping("api/valute")
public class ValutaController {

	@Autowired
	private ValutaService valutaService;

	@GetMapping
	public List<ValutaDto> get() {
		return valutaService.getAll();
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody @Valid ValutaDto valuta) {
		ValutaDto added = valutaService.add(valuta);

		if (added == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(added);
		}
	}

}
