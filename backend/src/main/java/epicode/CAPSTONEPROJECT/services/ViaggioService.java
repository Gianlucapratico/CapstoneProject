package epicode.CAPSTONEPROJECT.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Viaggio;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.repositories.ViaggioRepository;

@Service
public class ViaggioService {
	@Autowired
	ViaggioRepository viaggioRepo;

	// ***** CREATE *****
	public Viaggio create(Viaggio v) {
		Viaggio newViaggio = new Viaggio(v.getCitta(), v.getStato(), v.getDataPartenza(), v.getDataArrivo(),
				v.getDescrizione(), v.getPrezzo(), v.getUrlImg());

		return viaggioRepo.save(newViaggio);
	}

	// ***** READ *****
	public Page<Viaggio> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return viaggioRepo.findAll(pageable);
	}

	public Viaggio findById(UUID viaggioId) throws NotFoundException {
		return viaggioRepo.findById(viaggioId).orElseThrow(() -> new NotFoundException("Viaggio non trovato"));

	}

	// ***** UPDATE *****
	public Viaggio update(UUID viaggioId, Viaggio d) throws NotFoundException {
		Viaggio viaggioFound = this.findById(viaggioId);

		viaggioFound.setId(viaggioId);
		viaggioFound.setCitta(d.getCitta());
		viaggioFound.setStato(d.getStato());
		viaggioFound.setDataPartenza(d.getDataPartenza());
		viaggioFound.setDataArrivo(d.getDataArrivo());
		viaggioFound.setDescrizione(d.getDescrizione());
		viaggioFound.setPrezzo(d.getPrezzo());
		viaggioFound.setUrlImg(d.getUrlImg());

		return viaggioRepo.save(viaggioFound);
	}

	// ***** DELETE *****
	public void delete(UUID viaggioId) throws NotFoundException {
		Viaggio viaggioFound = this.findById(viaggioId);

		viaggioRepo.delete(viaggioFound);

	}
}
