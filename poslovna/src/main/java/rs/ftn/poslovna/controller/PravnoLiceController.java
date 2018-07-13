package rs.ftn.poslovna.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.validation.Valid;

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

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import rs.ftn.poslovna.dto.LiceDto;
import rs.ftn.poslovna.dto.PravnoLiceDto;
import rs.ftn.poslovna.repository.PravnoLiceRepository;
import rs.ftn.poslovna.service.PravnoLiceService;

@RestController
@RequestMapping("api/lica/pravnalica")
public class PravnoLiceController {
	
	@Autowired
	private PravnoLiceService plService;
	
	@Autowired
	private PravnoLiceRepository prRepository;
	
	@Autowired
	private DataSource dataSource;
	
	@PostMapping
	private ResponseEntity<?> addPravnoLice(@RequestBody @Valid PravnoLiceDto pldto){
		 pldto = plService.addPravnoLice(pldto);
		 
		 if(pldto == null) {
			 return new ResponseEntity<>("Greska prilikom kreiranja pravnog lica", HttpStatus.BAD_REQUEST);
		 }
		 
		 return new ResponseEntity<>(pldto,HttpStatus.CREATED);
	}
	
	@GetMapping
	private List<PravnoLiceDto> getPravnaLica(){
		return plService.getPravnaLica();
	}
	
	@GetMapping("/banke")
	private List<PravnoLiceDto> getBanke(){
		return plService.getBanke();
	}
	
	@GetMapping("/lica")
	private List<LiceDto> getLica(){
		return plService.getLica();
	}
	
	@GetMapping("/banke/{id}")
	public ResponseEntity<?> get(@PathVariable int id) {
		if(prRepository.findBanka(id)==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Map<String, Object> mapa = new HashMap<String,Object>();
		mapa.put("IdBanke", id);
		try {
			JasperPrint jp = JasperFillManager.fillReport(
					getClass().getResource("/SpisakRacuna.jasper").openStream(), mapa,
					dataSource.getConnection());
			// eksport
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			
			JasperExportManager.exportReportToPdfStream(jp, stream);
			
			return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"SpisakRacuna.pdf\"")
					.body(new InputStreamResource(new ByteArrayInputStream(stream.toByteArray())));
			
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
