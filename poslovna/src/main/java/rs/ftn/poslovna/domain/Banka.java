package rs.ftn.poslovna.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Banka {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(columnDefinition = "CHAR(3)")
	private String sifraBanke;

	@Column(precision = 9, scale = 0)
	private BigDecimal pib;

	@Column(nullable = false, length = 120)
	private String naziv;

	@Column(nullable = false, length = 120)
	private String adresa;

	@Column(nullable = true, length = 128)
	private String email;

	@Column(nullable = true, length = 128)
	private String web;

	@Column(nullable = true, length = 20)
	private String telefon;

	@Column(nullable = true, length = 20)
	private String faks;

	@Column(nullable = false)
	private boolean banka;

	@OneToMany(mappedBy = "banka", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Racun> racuni;

	public Banka() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSifraBanke() {
		return sifraBanke;
	}

	public void setSifraBanke(String sifraBanke) {
		this.sifraBanke = sifraBanke;
	}

	public BigDecimal getPib() {
		return pib;
	}

	public void setPib(BigDecimal pib) {
		this.pib = pib;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
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

	public boolean isBanka() {
		return banka;
	}

	public void setBanka(boolean banka) {
		this.banka = banka;
	}

	public List<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}

}
