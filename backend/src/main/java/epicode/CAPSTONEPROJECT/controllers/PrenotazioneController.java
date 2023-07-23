package epicode.CAPSTONEPROJECT.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import epicode.CAPSTONEPROJECT.entities.User;
import epicode.CAPSTONEPROJECT.entities.Viaggio;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.repositories.UsersRepository;
import epicode.CAPSTONEPROJECT.services.PrenotazioneService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

	@Autowired
	private PrenotazioneService prenotazioneService;
	@Autowired
	private UsersRepository usersRepo;

	@GetMapping("")
	public Page<Prenotazione> getAllPrenotazioni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "dataPrenotazione") String sortBy) {

		return prenotazioneService.findAll(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public Prenotazione getPrenotazioneById(@PathVariable UUID id) {
		Prenotazione prenotazione = prenotazioneService.findById(id);
		Viaggio viaggio = prenotazione.getViaggio();
		User user = prenotazione.getUser();
		if (viaggio != null) {
			String citta = viaggio.getCitta();

		} else {

		}
		return prenotazione;
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Prenotazione createPrenotazione(@RequestBody Prenotazione prenotazione) {
		UUID userId = prenotazione.getUser().getId(); // Ottieni l'ID dell'utente dalla prenotazione

		User user = usersRepo.findById(userId)
				.orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + userId));

		prenotazione.setUser(user); // Imposta l'utente corretto nella prenotazione

		return prenotazioneService.create(prenotazione);
	}

	@PutMapping("/{id}")

	public Prenotazione updatePrenotazione(@PathVariable UUID id, @RequestBody Prenotazione prenotazione) {
		return prenotazioneService.update(id, prenotazione);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePrenotazione(@PathVariable UUID id) {
		try {
			prenotazioneService.deletePrenotazione(id);
		} catch (NotFoundException e) {
			// Gestisci l'eccezione NotFoundException se necessario
			// Ad esempio, restituisci una risposta con errore appropriata
		}
	}
}
