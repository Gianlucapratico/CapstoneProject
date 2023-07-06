package epicode.CAPSTONEPROJECT.controllers;

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

import epicode.CAPSTONEPROJECT.entities.Destinazione;
import epicode.CAPSTONEPROJECT.services.DestinazioneService;

@RestController
@RequestMapping("/api/destinazioni")
public class DestinazioneController {
	@Autowired
	private DestinazioneService destinazioneService;

	@GetMapping("")
	public Page<Destinazione> getAllDestinazioni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "citta") String sortBy) {

		return destinazioneService.findAll(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public Destinazione getDestinazioneById(@PathVariable UUID id) {
		return destinazioneService.findById(id);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Destinazione createDestinazione(@RequestBody Destinazione destinazione) {
		return destinazioneService.create(destinazione);
	}

	@PutMapping("/{id}")

	public Destinazione updateDestinazione(@PathVariable UUID id, @RequestBody Destinazione destinazione) {
		return destinazioneService.update(id, destinazione);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDestinazione(@PathVariable UUID id) {

		destinazioneService.delete(id);
	}
}
