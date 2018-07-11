package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.Lice;

@Repository
public interface LiceRepository extends JpaRepository<Lice, Integer>{

}
