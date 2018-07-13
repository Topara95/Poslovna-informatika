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
import rs.ftn.poslovna.dto.FizickoLiceDto;
import rs.ftn.poslovna.repository.FizickoLiceRepository;
import rs.ftn.poslovna.service.FizickoLiceService;

@RestController
@RequestMapping("api/lica/fizickalica")
public class FizickoLiceController {

	
	@Autowired 
	private DataSource dataSource;
	
	@Autowired
	private FizickoLiceService fizliceService;
	
	@Autowired
	private FizickoLiceRepository fizickoLiceRepository;

	@PostMapping
	private ResponseEntity<?> addFizickoLice(@RequestBody @Valid FizickoLiceDto fizliceDto) {
		FizickoLiceDto dodato = fizliceService.addFizickoLice(fizliceDto);

		if (dodato == null) {
			return new ResponseEntity<>("Greska prilikom dodavanja fizickog lica", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(dodato, HttpStatus.CREATED);
	}

	@GetMapping
	private List<FizickoLiceDto> getAllFizickoLice() {
		return fizliceService.getAllFizickoLice();
	}

	@GetMapping("{id}")
	public ResponseEntity<?> get(@PathVariable int id,@RequestParam(name="from") Date from,@RequestParam(name="to") Date to) {
		if(!fizickoLiceRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Map<String, Object> mapa = new HashMap<String,Object>();
		mapa.put("Id_klienta", id);
		mapa.put("DateFrom", from);
		mapa.put("DateTo", to);
		try {
			JasperPrint jp = JasperFillManager.fillReport(
					getClass().getResource("/IzvodLica.jasper").openStream(), mapa,
					dataSource.getConnection());
			// eksport
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			
			JasperExportManager.exportReportToPdfStream(jp, stream);
			
			return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"izvodLica.pdf\"")
					.body(new InputStreamResource(new ByteArrayInputStream(stream.toByteArray())));
			
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
