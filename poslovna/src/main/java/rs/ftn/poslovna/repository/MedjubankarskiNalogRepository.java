package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.MedjubankarskiNalog;

@Repository
public interface MedjubankarskiNalogRepository extends JpaRepository<MedjubankarskiNalog, Integer> {

}
