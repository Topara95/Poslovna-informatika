package rs.ftn.poslovna.dto;

import java.text.SimpleDateFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import rs.ftn.poslovna.domain.Racun;

public class RacunDto {
	
	private Long id;
	
	@NotBlank
	@Size(min = 18, max = 18)
	private String brojRacuna;
	
	private String datumOtvaranja;
	
	private boolean validan;
	
	private int liceId;
	
	private int bankaId;
	
	private int valutaId;
	
	public RacunDto() {
		
	}
	
	public RacunDto(Racun racun) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		this.id = racun.getId();
		this.brojRacuna = racun.getBrojRacuna();
		this.datumOtvaranja = format.format(racun.getDatumOtvaranja());
		this.validan = racun.isValidan();
		this.liceId = racun.getLice().getId();
		this.bankaId = racun.getBanka().getId();
		this.valutaId = racun.getValuta().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public String getDatumOtvaranja() {
		return datumOtvaranja;
	}

	public void setDatumOtvaranja(String datumOtvaranja) {
		this.datumOtvaranja = datumOtvaranja;
	}

	public boolean isValidan() {
		return validan;
	}

	public void setValidan(boolean validan) {
		this.validan = validan;
	}

	public int getBankaId() {
		return bankaId;
	}

	public void setBankaId(int bankaId) {
		this.bankaId = bankaId;
	}

	public int getValutaId() {
		return valutaId;
	}

	public void setValutaId(int valutaId) {
		this.valutaId = valutaId;
	}

	public int getLiceId() {
		return liceId;
	}

	public void setLiceId(int liceId) {
		this.liceId = liceId;
	}
	
}
