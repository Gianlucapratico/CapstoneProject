package epicode.CAPSTONEPROJECT.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Provincia;
import epicode.CAPSTONEPROJECT.repositories.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepo;

	public Provincia createProvincia(Provincia provincia) {
		Provincia newProvincia = new Provincia(provincia.getSigla(), provincia.getNome(), provincia.getRegione(),
				provincia.getComuni());
		return provinciaRepo.save(newProvincia);
	}

	public List<Provincia> getAllProvince() {
		return provinciaRepo.findAll();
	}

	public Provincia getProvinciaById(UUID id) {
		Optional<Provincia> optionalProvincia = provinciaRepo.findById(id);
		return optionalProvincia.orElse(null);
	}

	public Provincia updateProvincia(UUID id, Provincia provincia) {
		Optional<Provincia> optionalProvincia = provinciaRepo.findById(id);
		if (optionalProvincia.isPresent()) {
			Provincia existingProvincia = optionalProvincia.get();
			existingProvincia.setSigla(provincia.getSigla());
			existingProvincia.setNome(provincia.getNome());
			existingProvincia.setRegione(provincia.getRegione());
			existingProvincia.setComuni(provincia.getComuni());
			return provinciaRepo.save(existingProvincia);
		} else {
			return null;
		}
	}

	public void deleteProvincia(UUID id) {
		provinciaRepo.deleteById(id);
	}

}
