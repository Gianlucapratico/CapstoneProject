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

import epicode.CAPSTONEPROJECT.entities.Comune;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.services.ComuneService;

@RestController
@RequestMapping("/api/comuni")
public class ComuneController {
	@Autowired
	private ComuneService comuneService;

	@GetMapping("")
	public Page<Comune> getAllComuni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "nomeProvincia") String sortBy) {

		return comuneService.findAll(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public Comune getComuneById(@PathVariable UUID id) {
		return comuneService.findById(id);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Comune createCliente(@RequestBody Comune comune) {
		return comuneService.create(comune);
	}

	@PutMapping("/{id}")
	public Comune updateComune(@PathVariable UUID id, @RequestBody Comune comune) throws NotFoundException {
		return comuneService.update(id, comune);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteComune(@PathVariable UUID id) throws NotFoundException {
		comuneService.delete(id);
	}

}
