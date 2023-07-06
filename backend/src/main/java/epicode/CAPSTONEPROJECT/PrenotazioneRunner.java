package epicode.CAPSTONEPROJECT;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.CAPSTONEPROJECT.entities.Cliente;
import epicode.CAPSTONEPROJECT.entities.Prenotazione;
import epicode.CAPSTONEPROJECT.entities.Viaggio;
import epicode.CAPSTONEPROJECT.repositories.ClienteRepository;
import epicode.CAPSTONEPROJECT.repositories.PrenotazioneRepository;
import epicode.CAPSTONEPROJECT.repositories.RecensioneRepository;
import epicode.CAPSTONEPROJECT.repositories.ViaggioRepository;

@Order(3)
@Component
public class PrenotazioneRunner implements CommandLineRunner {
	@Autowired
	PrenotazioneRepository prenotazionerepo;

	@Autowired
	ClienteRepository clienterepo;

	@Autowired
	RecensioneRepository recensioneRepository;

	@Autowired
	ViaggioRepository viaggioRepository;

	@Override
	public void run(String... args) {
		Faker faker = new Faker(new Locale("it"));
		Random random = new Random();

		if (prenotazionerepo.count() == 0) {
			for (int i = 0; i < 20; i++) {
				try {
					LocalDate dataPrenotazione = LocalDate.now().minusDays(ThreadLocalRandom.current().nextInt(1, 30));
					LocalDate dataPartenza = LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(1, 10));
					LocalDate dataArrivo = dataPartenza.plusDays(ThreadLocalRandom.current().nextInt(1, 10));

					List<Cliente> clienti = clienterepo.findAll();
					Cliente clienteRandom = clienti.get(random.nextInt(clienti.size()));
					List<Viaggio> viaggi = viaggioRepository.findAll();
					Viaggio viaggioRandom = viaggi.get(random.nextInt(viaggi.size()));

					System.out.println("viaggi.size(): " + viaggi.size());
					System.out.println("Limiti inferiore e superiore: 1, 30");
					int randomIndex = ThreadLocalRandom.current().nextInt(viaggi.size());

					System.out.println("Random index: " + randomIndex);

					Prenotazione prenotazione = new Prenotazione(viaggioRandom, dataPrenotazione, clienteRandom,
							dataPartenza);
					prenotazionerepo.save(prenotazione);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}

}

//		
