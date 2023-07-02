package epicode.CAPSTONEPROJECT.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import epicode.CAPSTONEPROJECT.entities.Recensione;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.services.RecensioneService;

@RestController
@RequestMapping("/recensioni")
public class RecensioneController {

	@Autowired
	private RecensioneService recensioneService;

	// CREATE
	@PostMapping
	public Recensione createRecensione(@RequestBody Recensione recensione) {
		return recensioneService.create(recensione);
	}

	// READ ALL
	@GetMapping
	public List<Recensione> getAllRecensioni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		Page<Recensione> recensioni = recensioneService.findAll(page, size, sortBy);
		return recensioni.getContent();
	}

	// READ BY ID
	@GetMapping("/{recensioneId}")
	public Recensione getRecensioneById(@PathVariable UUID recensioneId) throws NotFoundException {
		return recensioneService.findById(recensioneId);
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
