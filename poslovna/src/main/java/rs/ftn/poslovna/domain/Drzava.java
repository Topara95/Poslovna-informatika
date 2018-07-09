package rs.ftn.poslovna.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Drzava {

	@Id
	@Column(name = "SIFRA_DRZAVA", columnDefinition = "NUMERIC(3)")
	private short id;

	@Column(length = 40, nullable = false)
	private String nazivDrzave;

	@OneToMany(mappedBy = "drzava", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Valuta> valute;

	public Drzava() {

	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getNazivDrzave() {
		return nazivDrzave;
	}

	public void setNazivDrzave(String nazivDrzave) {
		this.nazivDrzave = nazivDrzave;
	}

	public List<Valuta> getValute() {
		return valute;
	}

	public void setValute(List<Valuta> valute) {
		this.valute = valute;
	}

}
