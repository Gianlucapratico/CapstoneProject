package epicode.CAPSTONEPROJECT;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.CAPSTONEPROJECT.entities.Recensione;
import epicode.CAPSTONEPROJECT.repositories.RecensioneRepository;

@Component
public class RecensioneRunner implements CommandLineRunner {

	@Autowired
	RecensioneRepository recensioneRepository;

	@Override
	public void run(String... args) {
		Faker faker = new Faker(new Locale("it"));
		if (recensioneRepository.count() == 0) {
			for (int i = 0; i < 20; i++) {
				try {
					recensioneRepository
							.save(new Recensione(null, faker.lorem().sentence(), faker.number().numberBetween(1, 5)));
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}
