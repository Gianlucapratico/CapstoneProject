package epicode.CAPSTONEPROJECT.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Recensione;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.repositories.RecensioneRepository;

@Service
public class RecensioneService {

	@Autowired
	RecensioneRepository recensioneRepo;

	// ***** CREATE *****
	public Recensione create(Recensione recensione) {
		return recensioneRepo.save(recensione);
	}

	// ***** READ *****
	public Page<Recensione> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return recensioneRepo.findAll(pageable);
	}

	// read by Id
	public Recensione findById(UUID recensioneId) throws NotFoundException {
		return recensioneRepo.findById(recensioneId).orElseThrow(() -> new NotFoundException("Recensione non trovata"));
	}

	// ***** UPDATE *****
	public Recensione update(UUID recensioneId, Recensione recensione) throws NotFoundException {
		Recensione recensioneFound = this.findById(recensioneId);

		recensioneFound.setCommento(recensione.getCommento());
		recensioneFound.setValutazione(recensione.getValutazione());
		recensioneFound.setPrenotazione(recensione.getPrenotazione());

		return recensioneRepo.save(recensioneFound);
	}

	// ***** DELETE *****
	public void delete(UUID recensioneId) throws NotFoundException {
		Recensione recensioneFound = this.findById(recensioneId);

		recensioneRepo.delete(recensioneFound);
	}
}
