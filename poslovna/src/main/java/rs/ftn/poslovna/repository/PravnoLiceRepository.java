package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.PravnoLice;

@Repository
public interface PravnoLiceRepository extends JpaRepository<PravnoLice, Integer>{

}
