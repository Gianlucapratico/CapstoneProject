//package epicode.CAPSTONEPROJECT.controllers;
//
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import epicode.CAPSTONEPROJECT.entities.Cliente;
//import epicode.CAPSTONEPROJECT.services.ClienteService;
//
//@RestController
//@RequestMapping("/api/clienti")
//public class ClienteController {
//
//	@Autowired
//	private ClienteService clienteService;
//
//	@GetMapping("")
//	public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "cognome") String sortBy) {
//
//		return clienteService.findAll(page, size, sortBy);
//	}
//
//	@GetMapping("/{id}")
//	public Cliente getClienteById(@PathVariable UUID id) {
//		return clienteService.findById(id);
//	}
//
//	@PostMapping("")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Cliente createCliente(@RequestBody Cliente cliente) {
//		return clienteService.create(cliente);
//	}
//
//	@PutMapping("/{id}")
//
//	public Cliente updateCliente(@PathVariable UUID id, @RequestBody Cliente cliente) {
//		return clienteService.update(id, cliente);
//
//	}
//
//	@DeleteMapping("/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void deleteCliente(@PathVariable UUID id) {
//
//		clienteService.delete(id);
//	}
//}
