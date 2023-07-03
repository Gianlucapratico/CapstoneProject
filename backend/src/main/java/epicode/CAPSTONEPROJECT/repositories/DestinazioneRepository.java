package epicode.CAPSTONEPROJECT.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.CAPSTONEPROJECT.entities.Destinazione;

@Repository
public interface DestinazioneRepository extends JpaRepository<Destinazione, UUID> {

}
