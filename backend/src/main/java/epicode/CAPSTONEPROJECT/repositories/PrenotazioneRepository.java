package epicode.CAPSTONEPROJECT.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.CAPSTONEPROJECT.entities.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
//	List<Prenotazione> findByDataPrenotazione(LocalDate dataPrenotazione);
//
//	List<Prenotazione> findByDestinazione(Destinazione destinazione);
//
//	List<Prenotazione> findByCliente(Cliente cliente);
}
