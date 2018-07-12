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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class MedjubankarskiNalog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PORUKE")
	private int id;

	@Column(nullable = false, columnDefinition = "NUMERIC(15,2)")
	private BigDecimal ukupanIznos;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SIFRA_VALUTE", nullable = false)
	private Valuta valuta;

	@Column(nullable = false)
	private Date datumValute;

	@Column(nullable = false)
	private Date datum;

	@Column(nullable = false, columnDefinition = "CHAR(4)")
	private String tip;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RACUN_BANKE_DUZNIKA_ID", nullable = false)
	private Racun racunBankeDuznika;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RACUN_BANKE_POVERIOCA_ID", nullable = false)
	private Racun racunBankePoverioca;

	@ManyToMany
	@JoinTable(name = "izvod_naloga", joinColumns = @JoinColumn(name = "id_naloga"), inverseJoinColumns = @JoinColumn(name = "id_izvoda"))
	private Set<AnalitikaIzvoda> analitikeIzvoda;

	public MedjubankarskiNalog() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getUkupanIznos() {
		return ukupanIznos;
	}

	public void setUkupanIznos(BigDecimal ukupanIznos) {
		this.ukupanIznos = ukupanIznos;
	}

	public Valuta getValuta() {
		return valuta;
	}

	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Racun getRacunBankeDuznika() {
		return racunBankeDuznika;
	}

	public void setRacunBankeDuznika(Racun racunBankeDuznika) {
		this.racunBankeDuznika = racunBankeDuznika;
	}

	public Racun getRacunBankePoverioca() {
		return racunBankePoverioca;
	}

	public void setRacunBankePoverioca(Racun racunBankePoverioca) {
		this.racunBankePoverioca = racunBankePoverioca;
	}

	public Set<AnalitikaIzvoda> getAnalitikeIzvoda() {
		return analitikeIzvoda;
	}

	public void setAnalitikeIzvoda(Set<AnalitikaIzvoda> analitikeIzvoda) {
		this.analitikeIzvoda = analitikeIzvoda;
	}

}
