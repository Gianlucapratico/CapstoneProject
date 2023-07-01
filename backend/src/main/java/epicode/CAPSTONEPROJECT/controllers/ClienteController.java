package epicode.CAPSTONEPROJECT.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import epicode.CAPSTONEPROJECT.entities.Cliente;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.services.ClienteService;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<Page<Cliente>> getAllClienti(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		// Ottiene la lista dei clienti utilizzando il metodo findAll() del servizio
		Page<Cliente> clienti = clienteService.findAll(page, size, sortBy);

		// Restituisce la lista dei clienti come corpo della risposta HTTP con stato 200
		// (OK)
		return ResponseEntity.ok(clienti);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable UUID id) {
		try {
			Cliente cliente = clienteService.findById(id);
			return ResponseEntity.ok(cliente);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		Cliente createdCliente = clienteService.create(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable UUID id, @RequestBody Cliente cliente) {
		try {
			Cliente updatedCliente = clienteService.update(id, cliente);
			return ResponseEntity.ok(updatedCliente);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable UUID id) {
		try {
			clienteService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
