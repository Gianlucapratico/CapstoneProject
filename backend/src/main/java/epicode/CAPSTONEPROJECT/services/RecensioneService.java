package epicode.CAPSTONEPROJECT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Recensione;
import epicode.CAPSTONEPROJECT.repositories.RecensioneRepository;

@Service
public class RecensioneService {
	private final RecensioneRepository recensioneRepository;

	@Autowired
	public RecensioneService(RecensioneRepository recensioneRepository) {
		this.recensioneRepository = recensioneRepository;
	}

	public Recensione getRecensioneById(Long id) {
		return recensioneRepository.findById(id).orElse(null);
	}

	public List<Recensione> getAllRecensioni() {
		return recensioneRepository.findAll();
	}

	public Recensione saveRecensione(Recensione recensione) {
		return recensioneRepository.save(recensione);
	}

	public Recensione updateRecensione(Long id, Recensione recensione) {
		Recensione existingRecensione = recensioneRepository.findById(id).orElse(null);
		if (existingRecensione != null) {
			existingRecensione.setPrenotazione(recensione.getPrenotazione());
			existingRecensione.setCommento(recensione.getCommento());
			existingRecensione.setValutazione(recensione.getValutazione());
			return recensioneRepository.save(existingRecensione);
		} else {
			return null;
		}
	}

	public void deleteRecensione(Long id) {
		recensioneRepository.deleteById(id);
	}

	public Recensione createRecensione(Recensione recensione) {
		return recensioneRepository.save(recensione);
	}
}
