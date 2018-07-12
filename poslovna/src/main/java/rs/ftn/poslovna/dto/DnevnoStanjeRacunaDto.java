package rs.ftn.poslovna.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import rs.ftn.poslovna.domain.DnevnoStanjeRacuna;

public class DnevnoStanjeRacunaDto {
	
	private long id;
	
	private Date datumPrometa;
	
	@NotNull
	@Digits(integer = 15, fraction = 2)
	private BigDecimal prethodnoStanje;
	
	@NotNull
	@Digits(integer = 15, fraction = 2)
	private BigDecimal prometUKorist;
	
	@NotNull
	@Digits(integer = 15, fraction = 2)
	private BigDecimal prometNaTeret;
	
	@NotNull
	@Digits(integer = 15, fraction = 2)
	private BigDecimal novoStanje;
	
	private long racunId;
	
	public DnevnoStanjeRacunaDto() {
		
	}
	
	public DnevnoStanjeRacunaDto(DnevnoStanjeRacuna dsr) {
		this.id = dsr.getId();
		this.datumPrometa = dsr.getDatumPromenta();
		this.prethodnoStanje = dsr.getPrethodnoStanje();
		this.prometUKorist = dsr.getPrometUKorist();
		this.prometNaTeret = dsr.getPrometNaTeret();
		this.novoStanje = dsr.getNovoStanje();
		this.racunId = dsr.getRacun().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumPrometa() {
		return datumPrometa;
	}

	public void setDatumPrometa(Date datumPrometa) {
		this.datumPrometa = datumPrometa;
	}

	public BigDecimal getPrethodnoStanje() {
		return prethodnoStanje;
	}

	public void setPrethodnoStanje(BigDecimal prethodnoStanje) {
		this.prethodnoStanje = prethodnoStanje;
	}

	public BigDecimal getPrometUKorist() {
		return prometUKorist;
	}

	public void setPrometUKorist(BigDecimal prometUKorist) {
		this.prometUKorist = prometUKorist;
	}

	public BigDecimal getPrometNaTeret() {
		return prometNaTeret;
	}

	public void setPrometNaTeret(BigDecimal prometNaTeret) {
		this.prometNaTeret = prometNaTeret;
	}

	public BigDecimal getNovoStanje() {
		return novoStanje;
	}

	public void setNovoStanje(BigDecimal novoStanje) {
		this.novoStanje = novoStanje;
	}

	public long getRacunId() {
		return racunId;
	}

	public void setRacunId(long racunId) {
		this.racunId = racunId;
	}
	
}
