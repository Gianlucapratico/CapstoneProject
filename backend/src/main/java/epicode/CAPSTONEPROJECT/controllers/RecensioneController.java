package epicode.CAPSTONEPROJECT.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epicode.CAPSTONEPROJECT.entities.Prenotazione;
import epicode.CAPSTONEPROJECT.entities.Recensione;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.repositories.PrenotazioneRepository;
import epicode.CAPSTONEPROJECT.services.RecensioneService;

@RestController
@RequestMapping("/api/recensioni")
public class RecensioneController {

	@Autowired
	private RecensioneService recensioneService;
	@Autowired
	private PrenotazioneRepository prenotazioneRepo;

	@GetMapping("/{id}")
	public Recensione getRecensioneById(@PathVariable UUID id) {
		Recensione recensione = recensioneService.findById(id);

		if (recensione != null) {
			Prenotazione prenotazione = recensione.getPrenotazione();

			if (prenotazione != null) {
				UUID prenotazioneId = prenotazione.getId();
				// Fai qualcosa con l'ID della prenotazione, se necessario
			} else {
				// La recensione non è collegata a nessuna prenotazione
			}
		} else {
			// Recensione non trovata
		}

		return recensione;
	}

	// CREATE
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Recensione createRecensione(@RequestBody Recensione r) throws NotFoundException {
		UUID prenotazioneId = r.getPrenotazioneId(); // Ottieni l'ID della prenotazione dalla richiesta

		if (prenotazioneId == null) {
			throw new IllegalArgumentException("Recensione non associata a una prenotazione");
		}

		Prenotazione prenotazione = prenotazioneRepo.findById(prenotazioneId)
				.orElseThrow(() -> new NotFoundException("Prenotazione non trovata con ID: " + prenotazioneId));

		r.setPrenotazione(prenotazione);
		return recensioneService.create(r);
	}

	// READ ALL
	@GetMapping
	public List<Recensione> getAllRecensioni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		Page<Recensione> recensioni = recensioneService.findAll(page, size, sortBy);
		return recensioni.getContent();
	}

	// UPDATE
	@PutMapping("/{recensioneId}")
	public Recensione updateRecensione(@PathVariable UUID recensioneId, @RequestBody Recensione recensione)
			throws NotFoundException {
		return recensioneService.update(recensioneId, recensione);
	}

	// DELETE
	@DeleteMapping("/{recensioneId}")
	public void deleteRecensione(@PathVariable UUID recensioneId) throws NotFoundException {
		recensioneService.delete(recensioneId);
	}
}
