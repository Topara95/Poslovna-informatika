package rs.ftn.poslovna.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VALUTE")
public class Valuta {

	@Id
	@Column(name = "ID_VALUTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(columnDefinition = "CHAR(3)", nullable = false)
	private String zvanicnaSifra;

	@Column(length = 30, nullable = false)
	private String naziv;

	@Column(nullable = false)
	private boolean domicilna;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DRZAVA_ID", nullable = false)
	private Drzava drzava;
	
	public Valuta() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZvanicnaSifra() {
		return zvanicnaSifra;
	}

	public void setZvanicnaSifra(String zvanicnaSifra) {
		this.zvanicnaSifra = zvanicnaSifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public boolean isDomicilna() {
		return domicilna;
	}

	public void setDomicilna(boolean domicilna) {
		this.domicilna = domicilna;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}

}
