package epicode.CAPSTONEPROJECT;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.CAPSTONEPROJECT.entities.Prenotazione;
import epicode.CAPSTONEPROJECT.entities.Recensione;
import epicode.CAPSTONEPROJECT.repositories.PrenotazioneRepository;
import epicode.CAPSTONEPROJECT.repositories.RecensioneRepository;

@Component
public class RecensioneRunner implements CommandLineRunner {

	private final RecensioneRepository recensioneRepository;
	private final PrenotazioneRepository prenotazioneRepository;

	@Autowired
	public RecensioneRunner(RecensioneRepository recensioneRepository, PrenotazioneRepository prenotazioneRepository) {
		this.recensioneRepository = recensioneRepository;
		this.prenotazioneRepository = prenotazioneRepository;
	}

	@Override
	public void run(String... args) {
		// Esempio di utilizzo del repository delle prenotazioni
		List<Prenotazione> prenotazioni = prenotazioneRepository.findAll();

		// Genera recensioni casuali per le prenotazioni esistenti
		List<Recensione> recensioni = generateRandomRecensioni(prenotazioni);

		// Salva le recensioni nel repository
		recensioneRepository.saveAll(recensioni);

		System.out.println("Recensioni casuali generate e salvate:");
		recensioni.forEach(System.out::println);
	}

	private List<Recensione> generateRandomRecensioni(List<Prenotazione> prenotazioni) {
		List<Recensione> recensioni = new ArrayList<>();

		Faker faker = new Faker(); // Creazione di un oggetto Faker per generare dati fake

		for (Prenotazione prenotazione : prenotazioni) {
			// Genera dati casuali per la recensione
			String randomCommento = faker.lorem().sentence();
			int randomValutazione = faker.number().numberBetween(1, 5);

			// Crea l'oggetto Recensione
			Recensione recensione = new Recensione(prenotazione, randomCommento, randomValutazione);

			// Aggiungi la recensione alla lista
			recensioni.add(recensione);
		}

		return recensioni;
	}
}
