package epicode.CAPSTONEPROJECT.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.CAPSTONEPROJECT.entities.Viaggio;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, UUID> {
	// Eventuali metodi personalizzati per le operazioni sui viaggi
}
