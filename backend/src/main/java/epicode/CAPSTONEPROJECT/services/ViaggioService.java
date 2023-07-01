package epicode.CAPSTONEPROJECT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Viaggio;
import epicode.CAPSTONEPROJECT.repositories.ViaggioRepository;

@Service
public class ViaggioService {
	private final ViaggioRepository viaggioRepository;

	@Autowired
	public ViaggioService(ViaggioRepository viaggioRepository) {
		this.viaggioRepository = viaggioRepository;
	}

	// Metodi per gestire le operazioni sui viaggi

	public Viaggio getViaggioById(Long id) {
		return viaggioRepository.findById(id).orElse(null);
	}

	public List<Viaggio> getAllViaggi() {
		return viaggioRepository.findAll();
	}

	public Viaggio saveViaggio(Viaggio viaggio) {
		return viaggioRepository.save(viaggio);
	}

	public void deleteViaggioById(Long id) {
		viaggioRepository.deleteById(id);
	}
}
