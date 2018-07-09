package rs.ftn.poslovna.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DnevnoStanjeRacuna {

	@Id
	@Column(name = "BROJ_IZVODA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumPromenta;

	@Column(precision = 15, scale = 2, nullable = false)
	private BigDecimal prethodnoStanje;

	@Column(precision = 15, scale = 2, nullable = false)
	private BigDecimal prometUKorist;

	@Column(precision = 15, scale = 2, nullable = false)
	private BigDecimal prometNaTeret;

	@Column(precision = 15, scale = 2, nullable = false)
	private BigDecimal novoStanje;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "ID_RACUNA", nullable = false)
	private Racun racun;

	public DnevnoStanjeRacuna() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumPromenta() {
		return datumPromenta;
	}

	public void setDatumPromenta(Date datumPromenta) {
		this.datumPromenta = datumPromenta;
	}

	public BigDecimal getPrethodnoStanje() {
		return prethodnoStanje;
	}

	public void setPrethodnoStanje(BigDecimal prethodnoStanje) {
		this.prethodnoStanje = prethodnoStanje;
	}

	public BigDecimal getPrometUKorist() {
		return prometUKorist;
	}

	public void setPrometUKorist(BigDecimal prometUKorist) {
		this.prometUKorist = prometUKorist;
	}

	public BigDecimal getPrometNaTeret() {
		return prometNaTeret;
	}

	public void setPrometNaTeret(BigDecimal prometNaTeret) {
		this.prometNaTeret = prometNaTeret;
	}

	public BigDecimal getNovoStanje() {
		return novoStanje;
	}

	public void setNovoStanje(BigDecimal novoStanje) {
		this.novoStanje = novoStanje;
	}

	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

}
