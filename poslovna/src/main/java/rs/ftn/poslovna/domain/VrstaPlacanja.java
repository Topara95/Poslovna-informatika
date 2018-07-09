package rs.ftn.poslovna.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VrstaPlacanja {

	@Id
	@Column(name = "OZNAKA_VRSTE", columnDefinition = "NUMERIC(3)")
	private short id;

	@Column(name = "NAZIV_VRSTE_PLACANJA", nullable = false, length = 120)
	private String naziv;

	public VrstaPlacanja() {

	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
