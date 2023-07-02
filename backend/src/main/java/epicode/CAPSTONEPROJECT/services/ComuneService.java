package epicode.CAPSTONEPROJECT.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Comune;
import epicode.CAPSTONEPROJECT.entities.Provincia;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.repositories.ComuneRepository;
import epicode.CAPSTONEPROJECT.repositories.ProvinciaRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepo;

	@Autowired
	ProvinciaRepository provinciaRepo;

	public List<Comune> readAll() {
		return comuneRepo.findAll();
	}

//	public Comune create(Comune c, Provincia p) {
//
//		String nomeProvincia = provinciaRepo.findByNome(p.getNome()).toString();
//		Comune newComune = null;
//		if (nomeProvincia.equals(c.getNomeProvincia())) {
//			c.setProvincia(p);
//			newComune = new Comune(c.getCodiceProvincia(), c.getProgressivoDelComune(), c.getDenominazioneInItaliano(),
//					c.getNomeProvincia());
//		}
//		return comuneRepo.save(newComune);
//	}
	public List<Comune> createComuniWithProvincia(List<Comune> comuni) {
		List<Provincia> province = provinciaRepo.findAll();

		for (Provincia provincia : province) {
			comuni = comuneRepo.findByProvincia(provincia.getNome().toString());

			for (Comune comune : comuni) {
				comune.setProvincia(provincia);
				comuneRepo.save(comune);

			}
		}
		return comuni;
	}

//	public Comune createComuniWithProvincia(Comune c, Provincia p) {
//		c.setProvincia(p);
//		comuneRepo.save(c);
//		return c;
//	}
	// ***** CREATE *****
	public Comune create(Comune c) {
		Comune newComune = new Comune(c.getCodiceProvincia(), c.getProgressivoDelComune(),
				c.getDenominazioneInItaliano(), c.getNomeProvincia(), c.getProvincia());

		return comuneRepo.save(newComune);
	}

	// ***** READ *****
	public Page<Comune> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return comuneRepo.findAll(pageable);
	}

	public Comune findById(UUID comuneId) throws NotFoundException {
		return comuneRepo.findById(comuneId).orElseThrow(() -> new NotFoundException("Comune non trovato"));
	}

	// ***** UPDATE *****
	public Comune update(UUID comuneId, Comune c) throws NotFoundException {
		Comune comuneFound = findById(comuneId);

		comuneFound.setCodiceProvincia(c.getCodiceProvincia());
		comuneFound.setProgressivoDelComune(c.getProgressivoDelComune());
		comuneFound.setDenominazioneInItaliano(c.getDenominazioneInItaliano());
		comuneFound.setNomeProvincia(c.getNomeProvincia());
		comuneFound.setProvincia(c.getProvincia());

		return comuneRepo.save(comuneFound);
	}

	public void delete(UUID comuneId) throws NotFoundException {
		Comune comuneFound = this.findById(comuneId);

		comuneRepo.delete(comuneFound);
	}

}
