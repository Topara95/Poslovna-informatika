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

import rs.ftn.poslovna.dto.DrzavaDto;
import rs.ftn.poslovna.service.DrzavaService;

@RestController
@RequestMapping("api/drzave")
public class DrzaveController {

	@Autowired
	private DrzavaService drzavaService;

	@GetMapping
	public List<DrzavaDto> get() {
		return drzavaService.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> post(@RequestBody @Valid DrzavaDto drzava) {
		DrzavaDto added = drzavaService.addDrzava(drzava);

		if (added == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(added);
		}
	}

}
