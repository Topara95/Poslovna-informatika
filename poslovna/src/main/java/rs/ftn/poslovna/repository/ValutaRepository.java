package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.Valuta;

@Repository
public interface ValutaRepository extends JpaRepository<Valuta, Integer> {

}
