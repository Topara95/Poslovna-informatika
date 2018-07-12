package rs.ftn.poslovna.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AnalitikaIzvoda {

	@Id
	@Column(name = "BROJ_STAVKE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 256, nullable = false)
	private String duznikNalogodavac;

	@Column(length = 256, nullable = false)
	private String svrhaPlacanja;

	@Column(length = 256, nullable = false)
	private String poverilacPrimalac;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumPrijema;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datumValute;

	@Column(length = 18, nullable = true)
	private String racunDuznika;

	@Column(columnDefinition = "NUMERIC(2)", nullable = true)
	private short modelZaduzenja;

	@Column(length = 20, nullable = true)
	private String pozivNaBrojZaduzenja;

	@Column(length = 18, nullable = true)
	private String racunPoverioca;

	@Column(columnDefinition = "NUMERIC(2)", nullable = true)
	private short modelOdobrenja;

	@Column(length = 20, nullable = true)
	private String pozivNaBrojOdobrenja;

	@Column(nullable = false)
	private boolean hitno;

	@Column(precision = 15, scale = 2, nullable = false)
	private BigDecimal iznos;

	@Column(columnDefinition = "NUMERIC(1)", nullable = false)
	private short tipGreske;

	@Column(columnDefinition = "CHAR(1)", nullable = true)
	private String status;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ID_STANJA", nullable = true)
	private DnevnoStanjeRacuna stanjeRacuna;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ID_NASELJENO_MEST0", nullable = true)
	private NaseljenoMesto naseljenoMesto;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ID_VRSTA_PLACANJA", nullable = true)
	private VrstaPlacanja vrstaPlacanja;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ID_VALUTE", nullable = true)
	private Valuta valuta;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "NALOG_ID", nullable = false)
	private Set<MedjubankarskiNalog> medjubankarskiNalozi;

	public AnalitikaIzvoda() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getPoverilacPrimalac() {
		return poverilacPrimalac;
	}

	public void setPoverilacPrimalac(String poverilacPrimalac) {
		this.poverilacPrimalac = poverilacPrimalac;
	}

	public Date getDatumPrijema() {
		return datumPrijema;
	}

	public void setDatumPrijema(Date datumPrijema) {
		this.datumPrijema = datumPrijema;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public String getRacunDuznika() {
		return racunDuznika;
	}

	public void setRacunDuznika(String racunDuznika) {
		this.racunDuznika = racunDuznika;
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

	public String getRacunPoverioca() {
		return racunPoverioca;
	}

	public void setRacunPoverioca(String racunPoverioca) {
		this.racunPoverioca = racunPoverioca;
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

	public boolean isHitno() {
		return hitno;
	}

	public void setHitno(boolean hitno) {
		this.hitno = hitno;
	}

	public BigDecimal getIznos() {
		return iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	public short getTipGreske() {
		return tipGreske;
	}

	public void setTipGreske(short tipGreske) {
		this.tipGreske = tipGreske;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DnevnoStanjeRacuna getStanjeRacuna() {
		return stanjeRacuna;
	}

	public void setStanjeRacuna(DnevnoStanjeRacuna stanjeRacuna) {
		this.stanjeRacuna = stanjeRacuna;
	}

	public NaseljenoMesto getNaseljenoMesto() {
		return naseljenoMesto;
	}

	public void setNaseljenoMesto(NaseljenoMesto naseljenoMesto) {
		this.naseljenoMesto = naseljenoMesto;
	}

	public VrstaPlacanja getVrstaPlacanja() {
		return vrstaPlacanja;
	}

	public void setVrstaPlacanja(VrstaPlacanja vrstaPlacanja) {
		this.vrstaPlacanja = vrstaPlacanja;
	}

	public Valuta getValuta() {
		return valuta;
	}

	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}

	public Set<MedjubankarskiNalog> getMedjubankarskiNalog() {
		return medjubankarskiNalozi;
	}

	public void setMedjubankarskiNalog(Set<MedjubankarskiNalog> medjubankarskiNalozi) {
		this.medjubankarskiNalozi = medjubankarskiNalozi;
	}

}
