package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.VrstaPlacanja;

@Repository
public interface VrstaPlacanjaRepository extends JpaRepository<VrstaPlacanja, Short> {

}
