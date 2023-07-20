package epicode.CAPSTONEPROJECT.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.CAPSTONEPROJECT.entities.Recensione;

@Repository
public interface RecensioneRepository extends JpaRepository<Recensione, UUID> {

}
