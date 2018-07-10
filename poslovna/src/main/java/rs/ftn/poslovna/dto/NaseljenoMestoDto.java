package rs.ftn.poslovna.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import rs.ftn.poslovna.domain.NaseljenoMesto;

public class NaseljenoMestoDto {

	private int id;

	@NotBlank
	@Length(max = 60)
	private String naziv;

	@NotBlank
	@Length(max = 12)
	private String ptt;

	private short drzavaId;

	public NaseljenoMestoDto() {

	}

	public NaseljenoMestoDto(NaseljenoMesto naseljenoMesto) {
		this.id = naseljenoMesto.getId();
		this.naziv = naseljenoMesto.getNaziv();
		this.ptt = naseljenoMesto.getPtt();
		this.drzavaId = naseljenoMesto.getDrzava().getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPtt() {
		return ptt;
	}

	public void setPtt(String ptt) {
		this.ptt = ptt;
	}

	public short getDrzavaId() {
		return drzavaId;
	}

	public void setDrzavaId(short drzavaId) {
		this.drzavaId = drzavaId;
	}

}
