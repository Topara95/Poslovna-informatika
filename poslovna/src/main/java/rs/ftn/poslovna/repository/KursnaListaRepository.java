package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.KursnaLista;

@Repository
public interface KursnaListaRepository extends JpaRepository<KursnaLista, Integer> {

}
