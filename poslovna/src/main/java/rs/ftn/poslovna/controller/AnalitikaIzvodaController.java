package rs.ftn.poslovna.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rs.ftn.poslovna.dto.AnalitikaDto;
import rs.ftn.poslovna.service.AnalitikaIzvodaService;

@RestController
@RequestMapping("api/analitika")
public class AnalitikaIzvodaController {

	@Autowired
	private AnalitikaIzvodaService analitikaIzvodaService;

	@GetMapping("{id}")
	public ResponseEntity<?> get(@PathVariable long id) {
		AnalitikaDto analitikaDto = analitikaIzvodaService.getOne(id);
		
		if (analitikaDto == null) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AnalitikaDto.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
			marshaller.marshal(analitikaDto, output);
			
			return ResponseEntity.ok()
		            .header("Content-Disposition", "attachment; filename=\"analitika.xml\"")
		            .body(new InputStreamResource(new ByteArrayInputStream(output.toByteArray())));
		} catch (JAXBException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public List<AnalitikaDto> get() {
		return analitikaIzvodaService.getAll();
	}

	@PostMapping
	public void post(@RequestBody @Valid AnalitikaDto analitikaDto) {
		analitikaIzvodaService.add(analitikaDto);

		/*if (added == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(added);
		}*/
	}
	
	@PostMapping("/xml")
	public void post(@RequestParam("file") MultipartFile file) throws JAXBException, IOException {
		JAXBContext jaxbContext = JAXBContext.newInstance(AnalitikaDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        AnalitikaDto analitikaDto = (AnalitikaDto) unmarshaller.unmarshal(file.getInputStream());
        
        analitikaIzvodaService.add(analitikaDto);
	}
}
