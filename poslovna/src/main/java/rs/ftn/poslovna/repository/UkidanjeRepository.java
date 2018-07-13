package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.Ukidanje;

@Repository
public interface UkidanjeRepository extends JpaRepository<Ukidanje,Integer>{

}
