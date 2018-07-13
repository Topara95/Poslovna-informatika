package rs.ftn.poslovna.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import rs.ftn.poslovna.domain.Ukidanje;

public class UkidanjeDto {
	
	private int id;
	
	private Date datumUkidanja;
	
	@NotNull
	private String sredstvaSePrenoseNaRacun;
	
	@NotNull
	private long racunId;
	
	public UkidanjeDto() {
		
	}
	
	public UkidanjeDto(Ukidanje ukidanje) {
		this.id = ukidanje.getId();
		this.datumUkidanja = ukidanje.getDatumUkidanja();
		this.sredstvaSePrenoseNaRacun = ukidanje.getSredstvaSePrenoseNaRacun();
		this.racunId = ukidanje.getRacun().getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatumUkidanja() {
		return datumUkidanja;
	}

	public void setDatumUkidanja(Date datumUkidanja) {
		this.datumUkidanja = datumUkidanja;
	}

	public String getSredstvaSePrenoseNaRacun() {
		return sredstvaSePrenoseNaRacun;
	}

	public void setSredstvaSePrenoseNaRacun(String sredstvaSePrenoseNaRacun) {
		this.sredstvaSePrenoseNaRacun = sredstvaSePrenoseNaRacun;
	}

	public long getRacunId() {
		return racunId;
	}

	public void setRacunId(long racunId) {
		this.racunId = racunId;
	}
	
}
