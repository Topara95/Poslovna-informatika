package rs.ftn.poslovna.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FizickoLice extends Lice {

	@Column(nullable = false, length = 120)
	private String ime;

	@Column(nullable = false, length = 120)
	private String prezime;

	@Column(unique = true, precision = 13, scale = 0)
	private BigDecimal jmbg;

	@Column(unique = true, precision = 9, scale = 0)
	private BigDecimal brojLicneKarte;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DRZAVLJANSTVO", nullable = false)
	private Drzava drzava;

	public FizickoLice() {

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

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

}
