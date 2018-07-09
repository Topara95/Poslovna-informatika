package rs.ftn.poslovna.domain;

import java.util.Date;

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

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Ukidanje {

	@Id
	@Column(name = "ID_UKIDANJA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@CreationTimestamp
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumUkidanja;

	@Column(nullable = false, length = 20)
	private String sredstvaSePrenoseNaRacun;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_RACUNA", nullable = false)
	private Racun racun;
	
	public Ukidanje() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatumUkidanja() {
		return datumUkidanja;
	}

	public void setDatumUkidanja(Date datumUkidanja) {
		this.datumUkidanja = datumUkidanja;
	}

	public String getSredstvaSePrenoseNaRacun() {
		return sredstvaSePrenoseNaRacun;
	}

	public void setSredstvaSePrenoseNaRacun(String sredstvaSePrenoseNaRacun) {
		this.sredstvaSePrenoseNaRacun = sredstvaSePrenoseNaRacun;
	}

	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}

}
