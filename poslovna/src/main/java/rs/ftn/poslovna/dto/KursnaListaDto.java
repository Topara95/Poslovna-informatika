package rs.ftn.poslovna.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import rs.ftn.poslovna.domain.KursnaLista;

public class KursnaListaDto {

	private int id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
	private Date datum;

	@NotNull
	@Digits(integer = 3, fraction = 0)
	private BigDecimal brojKursneListe;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
	private Date datumVazenja;

	@NotNull
	private int sifraBanke;

	public KursnaListaDto() {

	}

	public KursnaListaDto(KursnaLista kursnaLista) {
		this.id = kursnaLista.getId();
		this.datum = kursnaLista.getDatum();
		this.brojKursneListe = kursnaLista.getBrojKursneListe();
		this.datumVazenja = kursnaLista.getDatumVazenja();
		this.sifraBanke = kursnaLista.getPravnoLice().getId();
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

	public int getSifraBanke() {
		return sifraBanke;
	}

	public void setSifraBanke(int sifraBanke) {
		this.sifraBanke = sifraBanke;
	}

}
