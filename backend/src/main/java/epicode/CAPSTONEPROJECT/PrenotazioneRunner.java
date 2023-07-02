package epicode.CAPSTONEPROJECT;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.CAPSTONEPROJECT.entities.Prenotazione;
import epicode.CAPSTONEPROJECT.repositories.PrenotazioneRepository;

@Component
public class PrenotazioneRunner implements CommandLineRunner {
	@Autowired
	PrenotazioneRepository prenotazionerepo;

	@Override
	public void run(String... args) {
		Faker faker = new Faker(new Locale("it"));
		if (prenotazionerepo.count() == 0) {
			for (int i = 0; i < 20; i++) {
				try {
					Date prenotazione1 = faker.date().past(30, TimeUnit.DAYS);
					LocalDate dataPrenotazione = prenotazione1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					String nomePassegero = faker.name().firstName();
					Prenotazione prenotazione = new Prenotazione(dataPrenotazione, nomePassegero);
					prenotazionerepo.save(prenotazione);
					// Comune comune = new Comune

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}

//		
