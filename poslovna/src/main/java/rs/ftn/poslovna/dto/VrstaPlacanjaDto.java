package rs.ftn.poslovna.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import rs.ftn.poslovna.domain.VrstaPlacanja;

public class VrstaPlacanjaDto {

	@NotNull
	@Digits(integer = 3, fraction = 0)
	private short id;
	
	@NotNull
	private String naziv; 
	
	public VrstaPlacanjaDto() {
		
	}
	

	
	public VrstaPlacanjaDto(VrstaPlacanja vr) {
		this.id = vr.getId();
		this.naziv = vr.getNaziv();
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
	
}
