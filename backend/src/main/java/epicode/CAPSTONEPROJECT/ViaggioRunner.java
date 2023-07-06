package epicode.CAPSTONEPROJECT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.CAPSTONEPROJECT.entities.Destinazione;
import epicode.CAPSTONEPROJECT.repositories.DestinazioneRepository;

@Order(2)
@Component
public class DestinazioneRunner implements CommandLineRunner {
	@Autowired
	DestinazioneRepository destinazionerepo;

	@Override
	public void run(String... args) {
		Faker faker = new Faker();

		if (destinazionerepo.count() == 0) {
			for (int i = 0; i < 20; i++) {
				try {
					String citta = faker.address().city();
					String stato = faker.address().country();
					Destinazione destinazione = new Destinazione(citta, stato);
					destinazionerepo.save(destinazione);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}
