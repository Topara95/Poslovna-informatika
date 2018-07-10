package rs.ftn.poslovna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ftn.poslovna.domain.AtributiBanke;

@Repository
public interface AtributiBankeRepository extends JpaRepository<AtributiBanke,String>{

}
