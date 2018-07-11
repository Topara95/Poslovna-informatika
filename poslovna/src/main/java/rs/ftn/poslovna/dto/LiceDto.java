package rs.ftn.poslovna.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import rs.ftn.poslovna.domain.Lice;

public class LiceDto {
	
	private int id;
	
	@NotBlank
	@Size(max = 250)
	private String adresa;
	
	private int naseljenomestoId;
	
	public LiceDto(Lice lice) {
		this.id = lice.getId();
		this.adresa = lice.getAdresa();
		this.naseljenomestoId = lice.getNaseljenoMesto().getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getNaseljenomestoId() {
		return naseljenomestoId;
	}

	public void setNaseljenomestoId(int naseljenomestoId) {
		this.naseljenomestoId = naseljenomestoId;
	}

}
