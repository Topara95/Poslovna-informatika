package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.DnevnoStanjeRacuna;
import rs.ftn.poslovna.domain.Racun;

@Repository
public interface DnevnoStanjeRepository extends JpaRepository<DnevnoStanjeRacuna, Long> {

	DnevnoStanjeRacuna findFirstByRacunOrderByIdDesc(Racun racun);
	
}
