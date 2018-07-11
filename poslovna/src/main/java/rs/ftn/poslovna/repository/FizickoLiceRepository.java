package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.FizickoLice;

@Repository
public interface FizickoLiceRepository extends JpaRepository<FizickoLice,Integer>{

}
