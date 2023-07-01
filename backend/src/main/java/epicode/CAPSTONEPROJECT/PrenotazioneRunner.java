package epicode.CAPSTONEPROJECT;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.CAPSTONEPROJECT.entities.Comune;
import epicode.CAPSTONEPROJECT.entities.Prenotazione;
import epicode.CAPSTONEPROJECT.repositories.ComuneRepository;
import epicode.CAPSTONEPROJECT.repositories.PrenotazioneRepository;

@Component
public class PrenotazioneRunner implements CommandLineRunner {

	private final PrenotazioneRepository prenotazioneRepository;
	private final ComuneRepository comuneRepository;

	@Autowired
	public PrenotazioneRunner(PrenotazioneRepository prenotazioneRepository, ComuneRepository comuneRepository) {
		this.prenotazioneRepository = prenotazioneRepository;
		this.comuneRepository = comuneRepository;
	}

	@Override
	public void run(String... args) {
		// Esempio di utilizzo del repository dei comuni
		List<Comune> comuni = comuneRepository.findAll();

		// Genera prenotazioni random
		List<Prenotazione> prenotazioni = generateRandomPrenotazioni(comuni, 10); // Genera 10 prenotazioni random

		// Salva le prenotazioni nel repository
		prenotazioneRepository.saveAll(prenotazioni);

		System.out.println("Prenotazioni random generate e salvate:");
		prenotazioni.forEach(System.out::println);
	}

	private List<Prenotazione> generateRandomPrenotazioni(List<Comune> comuni, int quantity) {
		List<Prenotazione> prenotazioni = new ArrayList<>();

		Faker faker = new Faker();

		for (int i = 0; i < quantity; i++) {
			Comune randomComune = getRandomComune(comuni);
			LocalDate randomDataPrenotazione = faker.date().past(30, TimeUnit.DAYS).toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate();
			String randomNomePasseggero = faker.name().fullName();

			Prenotazione prenotazione = new Prenotazione(randomComune, randomDataPrenotazione, randomNomePasseggero);
			prenotazioni.add(prenotazione);
		}

		return prenotazioni;
	}

	private Comune getRandomComune(List<Comune> comuni) {
		Random random = new Random();
		int index = random.nextInt(comuni.size());
		return comuni.get(index);
	}
}
