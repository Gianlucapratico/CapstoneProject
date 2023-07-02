package epicode.CAPSTONEPROJECT.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Prenotazione;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.repositories.PrenotazioneRepository;

@Service
public class PrenotazioneService {

	@Autowired
	PrenotazioneRepository prenotazioneRepo;

	// ***** CREATE *****
	public Prenotazione create(Prenotazione p) {
		Prenotazione newPrenotazione = new Prenotazione(p.getComune(), p.getDataPrenotazione(), p.getNomePasseggero());

		return prenotazioneRepo.save(newPrenotazione);

	}

	// ***** READ *****
	public Page<Prenotazione> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return prenotazioneRepo.findAll(pageable);
	}

	// read by Id
	public Prenotazione findById(UUID clienteId) throws NotFoundException {
		return prenotazioneRepo.findById(clienteId).orElseThrow(() -> new NotFoundException("Cliente non trovato"));
	}

	// ***** UPDATE *****
	public Prenotazione update(UUID prenotazioneId, Prenotazione p) throws NotFoundException {
		Prenotazione prenotazioneFound = this.findById(prenotazioneId);

		prenotazioneFound.setDataPrenotazione(p.getDataPrenotazione());
		prenotazioneFound.setNomePasseggero(p.getNomePasseggero());
		prenotazioneFound.setComune(p.getComune());

		return prenotazioneRepo.save(prenotazioneFound);
	}

	// ***** DELETE *****
	public void delete(UUID prenotazioneId) throws NotFoundException {
		Prenotazione prenotazioneFound = this.findById(prenotazioneId);

		prenotazioneRepo.delete(prenotazioneFound);
	}

}
