package rs.ftn.poslovna.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import rs.ftn.poslovna.domain.Valuta;

public class ValutaDto {

	private int id;

	@NotBlank
	@Length(max = 3, min = 3)
	private String zvanicnaSifra;

	@NotBlank
	@Length(max = 30)
	private String naziv;

	private boolean domicilna;
	private short drzavaId;

	public ValutaDto() {

	}

	public ValutaDto(Valuta valuta) {
		this.id = valuta.getId();
		this.zvanicnaSifra = valuta.getZvanicnaSifra();
		this.naziv = valuta.getNaziv();
		this.domicilna = valuta.isDomicilna();
		this.drzavaId = valuta.getDrzava().getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZvanicnaSifra() {
		return zvanicnaSifra;
	}

	public void setZvanicnaSifra(String zvanicnaSifra) {
		this.zvanicnaSifra = zvanicnaSifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public boolean isDomicilna() {
		return domicilna;
	}

	public void setDomicilna(boolean domicilna) {
		this.domicilna = domicilna;
	}

	public short getDrzavaId() {
		return drzavaId;
	}

	public void setDrzavaId(short drzavaId) {
		this.drzavaId = drzavaId;
	}

}
