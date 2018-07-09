package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.Drzava;

@Repository
public interface DrzavaRepository extends JpaRepository<Drzava, Short> {

}
