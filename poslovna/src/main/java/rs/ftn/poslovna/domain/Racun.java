package rs.ftn.poslovna.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "RACUNI_KLIJENATA")
public class Racun {

	@Id
	@Column(name = "ID_RACUNA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 18, unique = true)
	private String brojRacuna;

	@CreationTimestamp
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumOtvaranja;

	@Column(name = "VAZECI", nullable = false)
	private boolean validan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "KLIJENT_ID", nullable = false)
	private Lice lice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BANKA_ID", nullable = false)
	private PravnoLice banka;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VALUTA_ID", nullable = false)
	private Valuta valuta;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "racun", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ukidanje> ukidanja;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "racun", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DnevnoStanjeRacuna> dnevnaStanja;
	
	public Racun() {
		
	}
	
	public Racun(String brojRacuna, Date datumOtvaranja, boolean validan, Lice lice, PravnoLice banka, Valuta valuta) {
		this.brojRacuna = brojRacuna;
		this.datumOtvaranja = datumOtvaranja;
		this.validan = validan;
		this.lice = lice;
		this.banka = banka;
		this.valuta = valuta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public Date getDatumOtvaranja() {
		return datumOtvaranja;
	}

	public void setDatumOtvaranja(Date datumOtvaranja) {
		this.datumOtvaranja = datumOtvaranja;
	}

	public boolean isValidan() {
		return validan;
	}

	public void setValidan(boolean validan) {
		this.validan = validan;
	}

	public Lice getLice() {
		return lice;
	}

	public void setLice(Lice lice) {
		this.lice = lice;
	}

	public PravnoLice getBanka() {
		return banka;
	}

	public void setBanka(PravnoLice banka) {
		this.banka = banka;
	}

	public Valuta getValuta() {
		return valuta;
	}

	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}

	public List<Ukidanje> getUkidanja() {
		return ukidanja;
	}

	public void setUkidanja(List<Ukidanje> ukidanja) {
		this.ukidanja = ukidanja;
	}

	public List<DnevnoStanjeRacuna> getDnevnaStanja() {
		return dnevnaStanja;
	}

	public void setDnevnaStanja(List<DnevnoStanjeRacuna> dnevnaStanja) {
		this.dnevnaStanja = dnevnaStanja;
	}

}
