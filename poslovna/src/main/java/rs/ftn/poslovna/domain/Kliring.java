package rs.ftn.poslovna.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="KLIRING_NALOZI")
public class Kliring {
	
	@Id
	@Column(name = "ID_poruke",nullable = false, length = 50)
	private String id;
	
	@Column(nullable = false, columnDefinition="NUMERIC(15,2)")
	private BigDecimal ukupanIznos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VALUTA_ID", nullable = false)
	private Valuta valuta;
	
	@Column(nullable = false)
	private Date datumValute;
	
	@Column(nullable = false)
	private Date datum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BANKA_DUZNIKA_ID", nullable = false)
	private Banka bankaDuznika;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BANKA_POVERIOCA_ID", nullable = false)
	private Banka bankaPoverioca;
	
	@OneToMany(mappedBy = "kliring", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PojedinacnoPlacanje> sekvencaPlacanja;
	
	public Kliring() {
		
	}

}
