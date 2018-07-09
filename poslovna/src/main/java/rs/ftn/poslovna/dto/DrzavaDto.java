package rs.ftn.poslovna.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import rs.ftn.poslovna.domain.Drzava;

public class DrzavaDto {

	@Digits(integer = 3, fraction = 0)
	private short id;

	@NotBlank
	private String nazivDrzave;

	public DrzavaDto() {
		
	}

	public DrzavaDto(Drzava drzava) {
		this.id = drzava.getId();
		this.nazivDrzave = drzava.getNazivDrzave();
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getNazivDrzave() {
		return nazivDrzave;
	}

	public void setNazivDrzave(String nazivDrzave) {
		this.nazivDrzave = nazivDrzave;
	}

}
