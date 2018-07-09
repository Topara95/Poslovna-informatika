package rs.ftn.poslovna.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "RTGS_nalozi")
public class RtgsNalog {

	@Id
	@Column(name = "ID_PORUKE", nullable = false, length = 50)
	private String id;

	@Column(nullable = false, length = 255)
	private String duznikNalogodavac;

	@Column(nullable = false, length = 255)
	private String svrhaPlacanja;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date datumNaloga;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumValute;

	@Column(nullable = false, columnDefinition = "NUMERIC(2)")
	private short modelZaduzenja;

	@Column(nullable = false, length = 20)
	private String pozivNaBrojZaduzenja;

	@Column(nullable = false, columnDefinition = "NUMERIC(2)")
	private short modelOdobrenja;

	@Column(nullable = false, length = 20)
	private String pozivNaBrojOdobrenja;

	@Column(nullable = false, columnDefinition = "NUMERIC(15,2)")
	private BigDecimal iznos;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RACUN_DUZNIKA_ID", nullable = false)
	private Racun racunDuznika;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RACUN_POVERIOCA_ID", nullable = false)
	private Racun racunPoverioca;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BANKA_DUZNIKA_ID", nullable = false)
	private Banka bankaDuznika;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BANKA_POVERIOCA_ID", nullable = false)
	private Banka bankaPoverioca;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VALUTA_ID", nullable = false)
	private Valuta valuta;

	public RtgsNalog() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDuznikNalogodavac() {
		return duznikNalogodavac;
	}

	public void setDuznikNalogodavac(String duznikNalogodavac) {
		this.duznikNalogodavac = duznikNalogodavac;
	}

	public String getSvrhaPlacanja() {
		return svrhaPlacanja;
	}

	public void setSvrhaPlacanja(String svrhaPlacanja) {
		this.svrhaPlacanja = svrhaPlacanja;
	}

	public Date getDatumNaloga() {
		return datumNaloga;
	}

	public void setDatumNaloga(Date datumNaloga) {
		this.datumNaloga = datumNaloga;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public short getModelZaduzenja() {
		return modelZaduzenja;
	}

	public void setModelZaduzenja(short modelZaduzenja) {
		this.modelZaduzenja = modelZaduzenja;
	}

	public String getPozivNaBrojZaduzenja() {
		return pozivNaBrojZaduzenja;
	}

	public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
	}

	public short getModelOdobrenja() {
		return modelOdobrenja;
	}

	public void setModelOdobrenja(short modelOdobrenja) {
		this.modelOdobrenja = modelOdobrenja;
	}

	public String getPozivNaBrojOdobrenja() {
		return pozivNaBrojOdobrenja;
	}

	public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
	}

	public BigDecimal getIznos() {
		return iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	public Racun getRacunDuznika() {
		return racunDuznika;
	}

	public void setRacunDuznika(Racun racunDuznika) {
		this.racunDuznika = racunDuznika;
	}

	public Racun getRacunPoverioca() {
		return racunPoverioca;
	}

	public void setRacunPoverioca(Racun racunPoverioca) {
		this.racunPoverioca = racunPoverioca;
	}

	public Banka getBankaDuznika() {
		return bankaDuznika;
	}

	public void setBankaDuznika(Banka bankaDuznika) {
		this.bankaDuznika = bankaDuznika;
	}

	public Banka getBankaPoverioca() {
		return bankaPoverioca;
	}

	public void setBankaPoverioca(Banka bankaPoverioca) {
		this.bankaPoverioca = bankaPoverioca;
	}

	public Valuta getValuta() {
		return valuta;
	}

	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}

}
