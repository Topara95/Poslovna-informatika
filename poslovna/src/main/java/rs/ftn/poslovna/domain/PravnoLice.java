package rs.ftn.poslovna.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PravnoLice extends Lice {

	@Column(nullable = false, length = 120)
	private String naziv;

	@Column(precision = 8, nullable = false, unique = true)
	private BigDecimal maticniBroj;

	@Column(precision = 9, nullable = false, unique = true)
	private BigDecimal pib;

	@Column(nullable = true, length = 128)
	private String email;

	@Column(nullable = true, length = 128)
	private String web;

	@Column(nullable = true, length = 20)
	private String telefon;

	@Column(nullable = true, length = 20)
	private String faks;

	@OneToOne
	@JoinColumn(nullable = true)
	private AtributiBanke atributiBanke;
	
	@OneToMany(mappedBy = "banka", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Racun> racuni;
	
	public PravnoLice() {

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

	public AtributiBanke getAtributiBanke() {
		return atributiBanke;
	}

	public void setAtributiBanke(AtributiBanke atributiBanke) {
		this.atributiBanke = atributiBanke;
	}

	public List<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}

}
