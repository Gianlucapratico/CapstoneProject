package epicode.CAPSTONEPROJECT.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Destinazione;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.repositories.DestinazioneRepository;

@Service
public class DestinazioneService {
	@Autowired
	DestinazioneRepository destinazioneRepo;

	// ***** CREATE *****
	public Destinazione create(Destinazione d) {
		Destinazione newDestinazione = new Destinazione(d.getCitta(), d.getStato());

		return destinazioneRepo.save(newDestinazione);
	}

	// ***** READ *****
	public Page<Destinazione> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return destinazioneRepo.findAll(pageable);
	}

	public Destinazione findById(UUID destinazioneId) throws NotFoundException {
		return destinazioneRepo.findById(destinazioneId)
				.orElseThrow(() -> new NotFoundException("Destinazione non trovata"));

	}

	// ***** UPDATE *****
	public Destinazione update(UUID destinazioneId, Destinazione d) throws NotFoundException {
		Destinazione destinazioneFound = this.findById(destinazioneId);

		destinazioneFound.setId(destinazioneId);
		destinazioneFound.setCitta(d.getCitta());
		destinazioneFound.setStato(d.getStato());

		return destinazioneRepo.save(destinazioneFound);
	}

	// ***** DELETE *****
	public void delete(UUID destinazioneId) throws NotFoundException {
		Destinazione destinazioneFound = this.findById(destinazioneId);

		destinazioneRepo.delete(destinazioneFound);

	}
}
