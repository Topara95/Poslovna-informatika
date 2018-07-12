package rs.ftn.poslovna.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import rs.ftn.poslovna.domain.Kurs;

public class KursDto {

	@NotNull
	@Digits(integer = 9, fraction = 0)
	private BigDecimal id;
	
	@NotNull
	@Digits(integer = 13, fraction = 4)
	private BigDecimal kupovni;
	
	
	private BigDecimal srednji;
	
	@NotNull
	@Digits(integer = 13, fraction = 4)
	private BigDecimal prodajni;
	
	private int sifraKursneListe;
	
	private int sifraOsnovneValute;
	
	private int sifraPremaValuti;
	
	public KursDto() {
		
	}
	
	public KursDto(Kurs kurs) {
		this.id = kurs.getId();
		this.kupovni=kurs.getKupovni();
		this.prodajni = kurs.getProdajni();
		this.srednji = kurs.getSrednji();
		this.sifraKursneListe = kurs.getKursnaLista().getId();
		this.sifraOsnovneValute= kurs.getOsnovnaValuta().getId();
		this.sifraPremaValuti = kurs.getPremaValuti().getId();
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

	public int getSifraKursneListe() {
		return sifraKursneListe;
	}

	public void setSifraKursneListe(int sifraKursneListe) {
		this.sifraKursneListe = sifraKursneListe;
	}

	public int getSifraOsnovneValute() {
		return sifraOsnovneValute;
	}

	public void setSifraOsnovneValute(int sifraOsnovneValute) {
		this.sifraOsnovneValute = sifraOsnovneValute;
	}

	public int getSifraPremaValuti() {
		return sifraPremaValuti;
	}

	public void setSifraPremaValuti(int sifraPremaValuti) {
		this.sifraPremaValuti = sifraPremaValuti;
	}
	
	
	
	
}
