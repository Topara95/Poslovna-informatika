package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.Racun;

@Repository
public interface RacunRepository extends JpaRepository<Racun, Long>{

}
