package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.Banka;

@Repository
public interface BankaRepository extends JpaRepository<Banka,String>{

}
