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

import epicode.CAPSTONEPROJECT.entities.Prenotazione;
import epicode.CAPSTONEPROJECT.services.PrenotazioneService;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

	@Autowired
	private PrenotazioneService prenotazioneService;

	@GetMapping("")
	public Page<Prenotazione> getAllPrenotazioni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "dataPrenotazione") String sortBy) {

		return prenotazioneService.findAll(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public Prenotazione getprenotazioneById(@PathVariable UUID id) {
		return prenotazioneService.findById(id);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Prenotazione createPrenotazione(@RequestBody Prenotazione prenotazione) {
		return prenotazioneService.create(prenotazione);
	}

	@PutMapping("/{id}")

	public Prenotazione updatePrenotazione(@PathVariable UUID id, @RequestBody Prenotazione prenotazione) {
		return prenotazioneService.update(id, prenotazione);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePrenotazione(@PathVariable UUID id) {

		prenotazioneService.delete(id);
	}

//	@GetMapping("/data/{dataPrenotazione}")
//	public List<Prenotazione> getPrenotazioniByDataPrenotazione(@PathVariable LocalDate dataPrenotazione) {
//		return prenotazioneService.getPrenotazioniByDataPrenotazione(dataPrenotazione);
//	}
//
//	@GetMapping("/cliente/{cliente}")
//	public List<Prenotazione> getPrenotazioniByNomePasseggero(@PathVariable Cliente cliente) {
//		return prenotazioneService.getPrenotazioniByCliente(cliente);
//	}

}
