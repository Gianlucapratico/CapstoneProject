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

import epicode.CAPSTONEPROJECT.entities.Viaggio;
import epicode.CAPSTONEPROJECT.services.ViaggioService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/viaggi")
public class ViaggioController {
	@Autowired
	private ViaggioService viaggioService;

	@GetMapping("")
	public Page<Viaggio> getAllViaggi(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "citta") String sortBy) {

		return viaggioService.findAll(page, size, sortBy);
	}

	@GetMapping("/{viaggioId}")
	public Viaggio getViaggioById(@PathVariable("viaggioId") UUID viaggioId) {
		return viaggioService.findById(viaggioId);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Viaggio createViaggio(@RequestBody Viaggio viaggio) {
		return viaggioService.create(viaggio);
	}

	@PutMapping("/{id}")

	public Viaggio updateViaggio(@PathVariable UUID id, @RequestBody Viaggio viaggio) {
		return viaggioService.update(id, viaggio);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteViaggio(@PathVariable UUID id) {

		viaggioService.delete(id);
	}
}
