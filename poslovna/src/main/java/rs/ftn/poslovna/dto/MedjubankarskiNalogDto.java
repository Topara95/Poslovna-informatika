package rs.ftn.poslovna.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import rs.ftn.poslovna.domain.MedjubankarskiNalog;

public class MedjubankarskiNalogDto {
	
	private int id;
	
	@NotNull
	@Digits(integer = 15, fraction = 2)
	private BigDecimal ukupanIznos;
	
	private int valutaId;
	
	private Date datumValute;
	
	private Date datum;
	
	@NotBlank
	@Size(min = 4, max = 4)
	private String tip;
	
	@NotNull
	private long racunBankeDuznikaId;
	
	@NotNull
	private long racunBankePoveriocaId;
	
	public MedjubankarskiNalogDto() {
		
	}
	
	public MedjubankarskiNalogDto(MedjubankarskiNalog mn) {
		this.id = mn.getId();
		this.ukupanIznos = mn.getUkupanIznos();
		this.valutaId = mn.getValuta().getId();
		this.datumValute = mn.getDatumValute();
		this.datum = mn.getDatum();
		this.tip = mn.getTip();
		this.racunBankeDuznikaId = mn.getRacunBankeDuznika().getId();
		this.racunBankePoveriocaId = mn.getRacunBankePoverioca().getId();
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

	public int getValutaId() {
		return valutaId;
	}

	public void setValutaId(int valutaId) {
		this.valutaId = valutaId;
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

	public long getRacunBankeDuznikaId() {
		return racunBankeDuznikaId;
	}

	public void setRacunBankeDuznikaId(long racunBankeDuznikaId) {
		this.racunBankeDuznikaId = racunBankeDuznikaId;
	}

	public long getRacunBankePoveriocaId() {
		return racunBankePoveriocaId;
	}

	public void setRacunBankePoveriocaId(long racunBankePoveriocaId) {
		this.racunBankePoveriocaId = racunBankePoveriocaId;
	}
	
}
