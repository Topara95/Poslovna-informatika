package rs.ftn.poslovna.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import rs.ftn.poslovna.domain.AtributiBanke;

public class AtributiBankeDto {

	@NotBlank
	@Size(min = 3, max = 3, message = "Sifra banke mora biti tacno 3 karaktera")
	private String id;

	@NotBlank
	@Size(min = 8, max = 8, message = "Swift kod mora biti dugacak 8 karaktera")
	private String swiftKod;
	
	public AtributiBankeDto() {
		
	}
	
	public AtributiBankeDto(AtributiBanke at) {
		this.id = at.getId();
		this.swiftKod = at.getSwiftKod();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSwiftKod() {
		return swiftKod;
	}

	public void setSwiftKod(String swiftKod) {
		this.swiftKod = swiftKod;
	}

}
