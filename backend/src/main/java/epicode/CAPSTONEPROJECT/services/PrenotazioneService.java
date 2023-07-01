package epicode.CAPSTONEPROJECT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Prenotazione;
import epicode.CAPSTONEPROJECT.repositories.PrenotazioneRepository;

@Service
public class PrenotazioneService {
	private final PrenotazioneRepository prenotazioneRepository;

	@Autowired
	public PrenotazioneService(PrenotazioneRepository prenotazioneRepository) {
		this.prenotazioneRepository = prenotazioneRepository;
	}

	// Metodi per gestire le operazioni sulle prenotazioni

	public Prenotazione getPrenotazioneById(Long id) {
		return prenotazioneRepository.findById(id).orElse(null);
	}

	public List<Prenotazione> getAllPrenotazioni() {
		return prenotazioneRepository.findAll();
	}

	public Prenotazione savePrenotazione(Prenotazione prenotazione) {
		return prenotazioneRepository.save(prenotazione);
	}

	public void deletePrenotazioneById(Long id) {
		prenotazioneRepository.deleteById(id);
	}
}
