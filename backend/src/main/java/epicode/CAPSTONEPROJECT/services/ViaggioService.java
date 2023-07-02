package epicode.CAPSTONEPROJECT.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Viaggio;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.repositories.ViaggioRepository;

@Service
public class ViaggioService {

	@Autowired
	private ViaggioRepository viaggioRepository;

	// CREATE
	public Viaggio createViaggio(Viaggio viaggio) {
		return viaggioRepository.save(viaggio);
	}

	// READ ALL
	public List<Viaggio> getAllViaggi() {
		return viaggioRepository.findAll();
	}

	// READ BY ID
	public Viaggio getViaggioById(UUID viaggioId) throws NotFoundException {
		return viaggioRepository.findById(viaggioId).orElseThrow(() -> new NotFoundException("Viaggio non trovato"));
	}

	// UPDATE
	public Viaggio updateViaggio(UUID viaggioId, Viaggio viaggio) throws NotFoundException {
		Viaggio viaggioFound = getViaggioById(viaggioId);

		viaggioFound.setDataPartenza(viaggio.getDataPartenza());
		viaggioFound.setDataArrivo(viaggio.getDataArrivo());
		viaggioFound.setComune(viaggio.getComune());

		return viaggioRepository.save(viaggioFound);
	}

	// DELETE
	public void deleteViaggio(UUID viaggioId) throws NotFoundException {
		Viaggio viaggioFound = getViaggioById(viaggioId);
		viaggioRepository.delete(viaggioFound);
	}
}
