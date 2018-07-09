package rs.ftn.poslovna.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KURS_U_VALUTI")
public class Kurs {

	@Id
	@Column(name = "REDNI_BROJ", columnDefinition = "NUMERIC(9, 0)", nullable = false)
	private BigDecimal id;

	@Column(columnDefinition = "NUMERIC(13, 4)", nullable = false)
	private BigDecimal kupovni;

	@Column(columnDefinition = "NUMERIC(13, 4)", nullable = false)
	private BigDecimal srednji;

	@Column(columnDefinition = "NUMERIC(13, 4)", nullable = false)
	private BigDecimal prodajni;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_KURSNE_LISTE", nullable = false)
	private KursnaLista kursnaLista;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OSNOVNA_VALUTA", nullable = false)
	private Valuta osnovnaValuta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PREMA_VALUTI", nullable = false)
	private Valuta premaValuti;

	public Kurs() {

	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getKupovni() {
		return kupovni;
	}

	public void setKupovni(BigDecimal kupovni) {
		this.kupovni = kupovni;
	}

	public BigDecimal getSrednji() {
		return srednji;
	}

	public void setSrednji(BigDecimal srednji) {
		this.srednji = srednji;
	}

	public BigDecimal getProdajni() {
		return prodajni;
	}

	public void setProdajni(BigDecimal prodajni) {
		this.prodajni = prodajni;
	}

	public KursnaLista getKursnaLista() {
		return kursnaLista;
	}

	public void setKursnaLista(KursnaLista kursnaLista) {
		this.kursnaLista = kursnaLista;
	}

	public Valuta getOsnovnaValuta() {
		return osnovnaValuta;
	}

	public void setOsnovnaValuta(Valuta osnovnaValuta) {
		this.osnovnaValuta = osnovnaValuta;
	}

	public Valuta getPremaValuti() {
		return premaValuti;
	}

	public void setPremaValuti(Valuta premaValuti) {
		this.premaValuti = premaValuti;
	}

}
