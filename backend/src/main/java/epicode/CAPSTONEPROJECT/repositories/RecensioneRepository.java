package epicode.CAPSTONEPROJECT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.CAPSTONEPROJECT.entities.Recensione;

@Repository
public interface RecensioneRepository extends JpaRepository<Recensione, Long> {
	// Eventuali metodi personalizzati per le operazioni sulle recensioni
}
