package rs.ftn.poslovna.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NaseljenoMesto {

	@Id
	@Column(name = "SIFRA_MESTA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 60, nullable = false)
	private String naziv;

	@Column(name = "PTToznaka", length = 12, nullable = false)
	private String ptt;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_DRZAVE", nullable = false)
	private Drzava drzava;

	public NaseljenoMesto() {

	}

	public NaseljenoMesto(String naziv, String ptt, Drzava drzava) {
		this.naziv = naziv;
		this.ptt = ptt;
		this.drzava = drzava;
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

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

}
