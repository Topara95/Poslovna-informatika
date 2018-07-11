package rs.ftn.poslovna.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import rs.ftn.poslovna.domain.FizickoLice;

public class FizickoLiceDto {
	
	private int id;
	
	@NotBlank
	@Size(max = 250)
	private String adresa;
	
	private int naseljenomestoId;
	
	@NotBlank
	@Size(max = 120)
	private String ime;
	
	@NotBlank
	@Size(max = 120)
	private String prezime;
	
	@NotNull
	@Digits(integer = 13, fraction = 0)
	private BigDecimal jmbg;
	
	@NotNull
	@Digits(integer = 9, fraction = 0)
	private BigDecimal brojLicneKarte;
	
	private short drzavaId;
	
	public FizickoLiceDto() {
		
	}
	
	public FizickoLiceDto(FizickoLice fizlice) {
		this.id = fizlice.getId();
		this.adresa = fizlice.getAdresa();
		this.naseljenomestoId = fizlice.getNaseljenoMesto().getId();
		this.ime = fizlice.getIme();
		this.prezime = fizlice.getPrezime();
		this.jmbg = fizlice.getJmbg();
		this.brojLicneKarte = fizlice.getBrojLicneKarte();
		this.drzavaId = fizlice.getDrzava().getId();
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

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public BigDecimal getJmbg() {
		return jmbg;
	}

	public void setJmbg(BigDecimal jmbg) {
		this.jmbg = jmbg;
	}

	public BigDecimal getBrojLicneKarte() {
		return brojLicneKarte;
	}

	public void setBrojLicneKarte(BigDecimal brojLicneKarte) {
		this.brojLicneKarte = brojLicneKarte;
	}

	public short getDrzavaId() {
		return drzavaId;
	}

	public void setDrzavaId(short drzavaId) {
		this.drzavaId = drzavaId;
	}
	
}
