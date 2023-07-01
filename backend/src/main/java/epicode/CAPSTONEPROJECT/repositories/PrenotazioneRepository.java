package epicode.CAPSTONEPROJECT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.CAPSTONEPROJECT.entities.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
	// Eventuali metodi personalizzati per le operazioni sulle prenotazioni
}
