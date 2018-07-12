package rs.ftn.poslovna.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.Kurs;

@Repository
public interface KursRepository extends JpaRepository<Kurs, BigDecimal>{

}
