package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.NaseljenoMesto;

@Repository
public interface NaseljenaMestaRepository extends JpaRepository<NaseljenoMesto, Integer> {

}
