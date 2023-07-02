package epicode.CAPSTONEPROJECT.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epicode.CAPSTONEPROJECT.entities.Viaggio;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.services.ViaggioService;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

	@Autowired
	private ViaggioService viaggioService;

	// CREATE
	@PostMapping
	public Viaggio createViaggio(@RequestBody Viaggio viaggio) {
		return viaggioService.createViaggio(viaggio);
	}

	// READ ALL
	@GetMapping
	public List<Viaggio> getAllViaggi() {
		return viaggioService.getAllViaggi();
	}

	// READ BY ID
	@GetMapping("/{viaggioId}")
	public Viaggio getViaggioById(@PathVariable UUID viaggioId) throws NotFoundException {
		return viaggioService.getViaggioById(viaggioId);
	}

	// UPDATE
	@PutMapping("/{viaggioId}")
	public Viaggio updateViaggio(@PathVariable UUID viaggioId, @RequestBody Viaggio viaggio) throws NotFoundException {
		return viaggioService.updateViaggio(viaggioId, viaggio);
	}

	// DELETE
	@DeleteMapping("/{viaggioId}")
	public void deleteViaggio(@PathVariable UUID viaggioId) throws NotFoundException {
		viaggioService.deleteViaggio(viaggioId);
	}
}
