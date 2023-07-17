//package epicode.CAPSTONEPROJECT.services;
//
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import epicode.CAPSTONEPROJECT.entities.Cliente;
//import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
//import epicode.CAPSTONEPROJECT.repositories.ClienteRepository;
//
//@Service
//public class ClienteService {
//
//	@Autowired
//	ClienteRepository clienteRepo;
//
//	// ***** CREATE *****
//	public Cliente create(Cliente c) {
//		Cliente newCliente = new Cliente(c.getNome(), c.getEmail(), c.getTelefono(), c.getCognome());
//
//		return clienteRepo.save(newCliente);
//	}
//
//	// ***** READ *****
//	public Page<Cliente> findAll(int page, int size, String sortBy) {
//		if (size < 0)
//			size = 0;
//		if (size > 100)
//			size = 100;
//
//		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//
//		return clienteRepo.findAll(pageable);
//	}
//
//	// read by Id
//	public Cliente findById(UUID clienteId) throws NotFoundException {
//		return clienteRepo.findById(clienteId).orElseThrow(() -> new NotFoundException("Cliente non trovato"));
//	}
//
//	// ***** UPDATE *****
//	public Cliente update(UUID clienteId, Cliente c) throws NotFoundException {
//		Cliente clienteFound = this.findById(clienteId);
//
//		clienteFound.setId(clienteId);
//		clienteFound.setNome(c.getNome());
//		clienteFound.setEmail(c.getEmail());
//		clienteFound.setTelefono(c.getTelefono());
//		clienteFound.setCognome(c.getCognome());
//		return clienteRepo.save(clienteFound);
//	}
//
//	// ***** DELETE *****
//	public void delete(UUID clienteId) throws NotFoundException {
//		Cliente clienteFound = this.findById(clienteId);
//
//		clienteRepo.delete(clienteFound);
//	}
//
//}
