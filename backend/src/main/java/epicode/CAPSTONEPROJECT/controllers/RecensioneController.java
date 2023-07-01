package epicode.CAPSTONEPROJECT.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epicode.CAPSTONEPROJECT.entities.Recensione;
import epicode.CAPSTONEPROJECT.services.RecensioneService;

@RestController
@RequestMapping("/recensioni")
public class RecensioneController {

	private final RecensioneService recensioneService;

	@Autowired
	public RecensioneController(RecensioneService recensioneService) {
		this.recensioneService = recensioneService;
	}

	@GetMapping
	public ResponseEntity<List<Recensione>> getAllRecensioni() {
		List<Recensione> recensioni = recensioneService.getAllRecensioni();
		return new ResponseEntity<>(recensioni, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Recensione> getRecensioneById(@PathVariable("id") Long id) {
		Recensione recensione = recensioneService.getRecensioneById(id);
		if (recensione != null) {
			return new ResponseEntity<>(recensione, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Recensione> createRecensione(@RequestBody Recensione recensione) {
		Recensione newRecensione = recensioneService.createRecensione(recensione);
		return new ResponseEntity<>(newRecensione, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Recensione> updateRecensione(@PathVariable("id") Long id,
			@RequestBody Recensione recensione) {
		Recensione updatedRecensione = recensioneService.updateRecensione(id, recensione);
		if (updatedRecensione != null) {
			return new ResponseEntity<>(updatedRecensione, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRecensione(@PathVariable("id") Long id) {
		recensioneService.deleteRecensione(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
