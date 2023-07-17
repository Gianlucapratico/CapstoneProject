package epicode.CAPSTONEPROJECT.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Prenotazione;
import epicode.CAPSTONEPROJECT.entities.User;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.repositories.PrenotazioneRepository;
import epicode.CAPSTONEPROJECT.repositories.UsersRepository;
import epicode.CAPSTONEPROJECT.repositories.ViaggioRepository;

@Service
public class PrenotazioneService {

	@Autowired
	PrenotazioneRepository prenotazioneRepo;

	@Autowired
	ViaggioRepository viaggioRepo;
	@Autowired
	UsersRepository usersRepo;

	// ***** CREATE *****
	public Prenotazione create(Prenotazione p) {

//		Viaggio viaggio = new Viaggio(p.getViaggio().getCitta(), p.getViaggio().getStato(),
//				p.getViaggio().getDataPartenza(), p.getViaggio().getDataArrivo(), p.getViaggio().getDescrizione(),
//				p.getViaggio().getPrezzo(), p.getViaggio().getUrlImg());
//		viaggioRepo.save(viaggio);
//
//		Cliente cliente = new Cliente(p.getCliente().getNome(), p.getCliente().getTelefono(), p.getCliente().getEmail(),
//				p.getCliente().getCognome());
//		clienteRepo.save(cliente);
//		Prenotazione newPrenotazione = new Prenotazione(p.getViaggio(), p.getDataPrenotazione(), p.getCliente(),
//				p.getRecensione());
		User user = usersRepo.findById(p.getUser().getId())
				.orElseThrow(() -> new NotFoundException("Utente non trovato con ID: " + p.getUser().getId()));
		Prenotazione newPrenotazione = new Prenotazione();
		newPrenotazione.setViaggio(p.getViaggio()); // Assegna l'oggetto Viaggio appena creato alla prenotazione
		newPrenotazione.setDataPrenotazione(p.getDataPrenotazione());
		newPrenotazione.setUser(user);
		newPrenotazione.setRecensione(p.getRecensione());

		return prenotazioneRepo.save(newPrenotazione);
	}

//	public List<Prenotazione> getPrenotazioniByDataPrenotazione(LocalDate dataPrenotazione) {
//		return prenotazioneRepo.findByDataPrenotazione(dataPrenotazione);
//	}
//
//	public List<Prenotazione> getPrenotazioniByDestinazione(Destinazione destinazione) {
//		return prenotazioneRepo.findByDestinazione(destinazione);
//	}
//
//	public List<Prenotazione> getPrenotazioniByCliente(Cliente cliente) {
//		return prenotazioneRepo.findByCliente(cliente);
//	}

	// ***** READ *****
	public Page<Prenotazione> findAll(int page, int size, String sortBy) {
		if (size < 0)
			size = 0;
		if (size > 100)
			size = 100;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return prenotazioneRepo.findAll(pageable);
	}

	// read by Id
	public Prenotazione findById(UUID prenotazioneId) throws NotFoundException {
		return prenotazioneRepo.findById(prenotazioneId)
				.orElseThrow(() -> new NotFoundException("Prenotazione non trovata"));
	}

	// ***** UPDATE *****
	public Prenotazione update(UUID prenotazioneId, Prenotazione p) throws NotFoundException {
		Prenotazione prenotazioneFound = this.findById(prenotazioneId);

		prenotazioneFound.setDataPrenotazione(p.getDataPrenotazione());
//		prenotazioneFound.setCliente(p.getCliente().getNome(), p.getCliente().getTelefono(), p.getCliente().getEmail(),
//				p.getCliente().getCognome());
//		Cliente cliente = prenotazioneFound.getCliente();
//		cliente.setNome(p.getCliente().getNome());
//		cliente.setTelefono(p.getCliente().getTelefono());
//		cliente.setEmail(p.getCliente().getEmail());
//		cliente.setCognome(p.getCliente().getCognome());
//		prenotazioneFound.setCliente(cliente);

//		prenotazioneFound.setCliente(p.getCliente());
		prenotazioneFound.setViaggio(p.getViaggio());
		prenotazioneFound.setDataPrenotazione(p.getDataPrenotazione());
		prenotazioneFound.setUser(p.getUser());
		prenotazioneFound.setRecensione(p.getRecensione());

		return prenotazioneRepo.save(prenotazioneFound);
	}

	// ***** DELETE *****
	public void delete(UUID prenotazioneId) throws NotFoundException {
		Prenotazione prenotazioneFound = this.findById(prenotazioneId);

		prenotazioneRepo.delete(prenotazioneFound);
	}

}
