package epicode.CAPSTONEPROJECT;

import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.CAPSTONEPROJECT.entities.Recensione;
import epicode.CAPSTONEPROJECT.repositories.PrenotazioneRepository;
import epicode.CAPSTONEPROJECT.repositories.RecensioneRepository;

@Order(4)
@Component
public class RecensioneRunner implements CommandLineRunner {

	@Autowired
	RecensioneRepository recensioneRepository;
	@Autowired
	PrenotazioneRepository prenotazionerepo;

	@Override
	public void run(String... args) {
		Faker faker = new Faker(new Locale("it"));
		Random random = new Random();
		if (recensioneRepository.count() == 0) {
			// List<Prenotazione> prenotazioni = prenotazionerepo.findAll();
			for (int i = 0; i < 20; i++) {
				try {
					String commento = faker.lorem().sentence();

					int valutazione = faker.random().nextInt(1, 10);
					// int randomPrenotazioniIndex = random.nextInt(prenotazioni.size());
					// Prenotazione prenotazioneRandom = prenotazioni.get(randomPrenotazioniIndex);
					// prenotazioni.remove(randomPrenotazioniIndex);
					Recensione recensione = new Recensione(commento, valutazione, null);
					recensioneRepository.save(recensione);

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}

//	@Override
//	public void run(String... args) {
//		Faker faker = new Faker(new Locale("it"));
//		Random random = new Random();
//		List<Prenotazione> prenotazioni = prenotazionerepo.findAll();
//		Prenotazione prenotazioneRandom = prenotazioni.get(random.nextInt(prenotazioni.size()));
//		if (recensioneRepository.count() == 0) {
//			for (int i = 0; i < 20; i++) {
//				try {
//					String commento = faker.lorem().sentence();
//					int valutazione = faker.random().nextInt(1, 10);
//					Recensione recensione = new Recensione(commento, valutazione, prenotazioneRandom);
//					recensioneRepository.save(recensione);
//
//				} catch (Exception e) {
//					System.out.println(e);
//				}
//			}
//		}
//	}
}
