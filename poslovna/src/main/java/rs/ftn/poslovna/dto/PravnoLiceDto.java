package rs.ftn.poslovna.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import rs.ftn.poslovna.domain.PravnoLice;

public class PravnoLiceDto {

	private int id;
	
	@NotBlank
	@Size(max = 250)
	private String adresa;
	
	private int naseljenomestoId;
	
	@NotBlank
	@Size(max = 120)
	private String naziv;
	
	@NotNull
	@Digits(integer = 8, fraction = 0)
	private BigDecimal maticniBroj;
	
	@NotNull
	@Digits(integer = 9, fraction = 0)
	private BigDecimal pib;
	
	@Size(max = 128)
	private String email;
	
	@Size(max = 128)
	private String web;
	
	@Size(max = 20)
	private String telefon;
	
	@Size(max = 20)
	private String faks;
	
	private String atributiBankeId;
	
	public PravnoLiceDto() {
		
	}
	
	public PravnoLiceDto(PravnoLice pl) {
		this.id = pl.getId();
		this.adresa = pl.getAdresa();
		this.naseljenomestoId = pl.getNaseljenoMesto().getId();
		this.naziv = pl.getNaziv();
		this.maticniBroj = pl.getMaticniBroj();
		this.pib = pl.getPib();
		this.email = pl.getEmail();
		this.web = pl.getWeb();
		this.telefon = pl.getTelefon();
		this.faks = pl.getFaks();
		if(pl.getAtributiBanke() != null) {
			this.atributiBankeId = pl.getAtributiBanke().getId();
		}
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

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public BigDecimal getMaticniBroj() {
		return maticniBroj;
	}

	public void setMaticniBroj(BigDecimal maticniBroj) {
		this.maticniBroj = maticniBroj;
	}

	public BigDecimal getPib() {
		return pib;
	}

	public void setPib(BigDecimal pib) {
		this.pib = pib;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getFaks() {
		return faks;
	}

	public void setFaks(String faks) {
		this.faks = faks;
	}

	public String getAtributiBankeId() {
		return atributiBankeId;
	}

	public void setAtributiBankeId(String atributiBankeId) {
		this.atributiBankeId = atributiBankeId;
	}
}
