package rs.ftn.poslovna.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ftn.poslovna.dto.AtributiBankeDto;
import rs.ftn.poslovna.service.AtributiBankeService;

@RestController
@RequestMapping("api/atributiBanke")
public class AtributiBankeController {
	
	@Autowired
	private AtributiBankeService abservice;
	
	
	@PostMapping
	public ResponseEntity<?> addAtributiBanke(@RequestBody @Valid AtributiBankeDto abdto){
		abdto = abservice.addAtributiBanke(abdto);
		if(abdto != null) {
			return new ResponseEntity<>(abdto,HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Greska prilikom kreiranja atributa banke", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping
	public List<AtributiBankeDto> getAllAtributiBanke(){
		return abservice.getAllAtributiBanke();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAtributiBanke(@PathVariable("id") String id){
		AtributiBankeDto abdto = abservice.getOne(id);
		if(abdto != null) {
			return new ResponseEntity<>(abdto,HttpStatus.OK);
		}
		return new ResponseEntity<>("Atributi Banke nisu pronadjeni", HttpStatus.NOT_FOUND);
	}

}
