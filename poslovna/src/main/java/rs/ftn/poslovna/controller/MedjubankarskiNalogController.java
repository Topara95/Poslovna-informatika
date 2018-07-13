package rs.ftn.poslovna.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ftn.poslovna.dto.AnalitikaDto;
import rs.ftn.poslovna.dto.MedjubankarskiNalogDto;
import rs.ftn.poslovna.service.MedjubankarskiNalogService;

@RestController
@RequestMapping("api/medjubankarskiNalozi")
public class MedjubankarskiNalogController {
	
	@Autowired
	private MedjubankarskiNalogService mnService;
	
	@GetMapping
	private List<MedjubankarskiNalogDto> getNalozi(){
		return mnService.getNalozi();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> get(@PathVariable int id) {
		MedjubankarskiNalogDto medjubankarskiNalogDto = mnService.getOne(id);
		
		if (medjubankarskiNalogDto == null) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(MedjubankarskiNalogDto.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
			marshaller.marshal(medjubankarskiNalogDto, output);
			
			return ResponseEntity.ok()
		            .header("Content-Disposition", "attachment; filename=\"medjubankarskiNalog.xml\"")
		            .body(new InputStreamResource(new ByteArrayInputStream(output.toByteArray())));
		} catch (JAXBException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
