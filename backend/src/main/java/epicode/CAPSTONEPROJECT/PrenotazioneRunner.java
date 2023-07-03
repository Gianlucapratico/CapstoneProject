package epicode.CAPSTONEPROJECT;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.CAPSTONEPROJECT.entities.Cliente;
import epicode.CAPSTONEPROJECT.entities.Destinazione;
import epicode.CAPSTONEPROJECT.entities.Prenotazione;
import epicode.CAPSTONEPROJECT.repositories.ClienteRepository;
import epicode.CAPSTONEPROJECT.repositories.DestinazioneRepository;
import epicode.CAPSTONEPROJECT.repositories.PrenotazioneRepository;
import epicode.CAPSTONEPROJECT.repositories.RecensioneRepository;

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
	DestinazioneRepository destinazioneRepository;

	@Override
	public void run(String... args) {
		Faker faker = new Faker(new Locale("it"));
		Random random = new Random();
		if (prenotazionerepo.count() == 0) {
			for (int i = 0; i < 20; i++) {
				try {
					Date prenotazione1 = faker.date().past(30, TimeUnit.DAYS);
					LocalDate dataPrenotazione = prenotazione1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate dataPartenza = prenotazione1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate dataArrivo = prenotazione1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					List<Cliente> clienti = clienterepo.findAll();
					Cliente clienteRandom = clienti.get(random.nextInt(clienti.size()));
//					List<Recensione> recensioni = recensioneRepository.findAll();
//					Recensione recensioneRandom = recensioni.get(random.nextInt(recensioni.size()));
					List<Destinazione> destinazioni = destinazioneRepository.findAll();
					Destinazione destinazioneRandom = destinazioni.get(random.nextInt(destinazioni.size()));

					Prenotazione prenotazione = new Prenotazione(destinazioneRandom, dataPrenotazione, clienteRandom,
							dataPartenza, dataArrivo);
					prenotazionerepo.save(prenotazione);

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}

//		
