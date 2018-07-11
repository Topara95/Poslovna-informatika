package rs.ftn.poslovna.domain;

import java.math.BigDecimal;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class KursnaLista {

	@Id
	@Column(name = "ID_KURSNE_LISTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date datum;

	@Column(precision = 3, scale = 0, nullable = false)
	private BigDecimal brojKursneListe;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PRIMENJUJE_SE_OD", nullable = false)
	private Date datumVazenja;

	@OneToMany(mappedBy = "kursnaLista", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Kurs> kursevi;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PRAVNO_LICE", nullable = false)
	private PravnoLice pravnoLice;

	public KursnaLista() {

	}

	public KursnaLista(BigDecimal brojKursneListe, Date datumVazenja, PravnoLice pravnoLice) {
		this.brojKursneListe = brojKursneListe;
		this.datumVazenja = datumVazenja;
		this.pravnoLice = pravnoLice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public BigDecimal getBrojKursneListe() {
		return brojKursneListe;
	}

	public void setBrojKursneListe(BigDecimal brojKursneListe) {
		this.brojKursneListe = brojKursneListe;
	}

	public Date getDatumVazenja() {
		return datumVazenja;
	}

	public void setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
	}

	public List<Kurs> getKursevi() {
		return kursevi;
	}

	public void setKursevi(List<Kurs> kursevi) {
		this.kursevi = kursevi;
	}
	
	public PravnoLice getPravnoLice() {
		return pravnoLice;
	}
	
	public void setPravnoLice(PravnoLice pravnoLice) {
		this.pravnoLice = pravnoLice;
	}

}
