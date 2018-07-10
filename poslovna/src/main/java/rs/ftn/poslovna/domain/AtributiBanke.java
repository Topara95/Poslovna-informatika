package rs.ftn.poslovna.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

@Entity
public class AtributiBanke {
	
	@Id
	@Column(name="SIFRA_BANKE",columnDefinition="CHAR(3)",nullable=false)
	private String id;
	
	@Column(nullable = true, length = 8)
	@NaturalId
	private String swiftKod;
	
	public AtributiBanke() {
		
	}
	
	public AtributiBanke(String id, String swiftKod) {
		this.id = id;
		this.swiftKod = swiftKod;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSwiftKod() {
		return swiftKod;
	}

	public void setSwiftKod(String swiftKod) {
		this.swiftKod = swiftKod;
	}

}
