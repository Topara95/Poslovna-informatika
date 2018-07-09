package rs.ftn.poslovna.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PravnoLice extends Klijent {

	@Column(nullable = false, length = 120)
	private String naziv;

	@Column(precision = 8, nullable = false, unique = true)
	private BigDecimal maticniBroj;

	@Column(precision = 9, nullable = false, unique = true)
	private BigDecimal pib;

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

}
