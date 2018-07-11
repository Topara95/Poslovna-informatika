package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.PravnoLice;

@Repository
public interface PravnoLiceRepository extends JpaRepository<PravnoLice, Integer>{

	@Query("select b from PravnoLice b where id = :id and atributiBanke is not null")
	PravnoLice findBanka(@Param("id") int id);
	
}
