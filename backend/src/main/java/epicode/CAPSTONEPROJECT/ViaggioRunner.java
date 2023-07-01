package epicode.CAPSTONEPROJECT;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.CAPSTONEPROJECT.entities.Comune;
import epicode.CAPSTONEPROJECT.entities.Viaggio;
import epicode.CAPSTONEPROJECT.repositories.ComuneRepository;
import epicode.CAPSTONEPROJECT.repositories.ProvinciaRepository;
import epicode.CAPSTONEPROJECT.repositories.ViaggioRepository;

@Component
public class ViaggioRunner implements CommandLineRunner {

	private final ViaggioRepository viaggioRepository;
	private final ComuneRepository comuneRepository;
	private final ProvinciaRepository provinciaRepository;

	@Autowired
	public ViaggioRunner(ViaggioRepository viaggioRepository, ComuneRepository comuneRepository,
			ProvinciaRepository provinciaRepository) {
		this.viaggioRepository = viaggioRepository;
		this.comuneRepository = comuneRepository;
		this.provinciaRepository = provinciaRepository;
	}

	@Override
	public void run(String... args) {
		// Generazione dati casuali per i viaggi
		Faker faker = new Faker();

		// Recupero tutti i comuni dal repository
		List<Comune> comuni = comuneRepository.findAll();

		// Generazione dei viaggi casuali
		List<Viaggio> viaggi = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Comune comuneCasuale = comuni.get(faker.random().nextInt(comuni.size()));

			LocalDate dataPartenza = faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			LocalDate dataArrivo = dataPartenza.plusDays(faker.random().nextInt(1, 10));

			Viaggio viaggio = new Viaggio(dataPartenza, dataArrivo, comuneCasuale);
			viaggi.add(viaggio);
		}

		// Salva i viaggi nel repository
		viaggioRepository.saveAll(viaggi);

		// Esempio di utilizzo del repository dei viaggi
		List<Viaggio> viaggiSalvati = viaggioRepository.findAll();
		System.out.println("Elenco dei viaggi:");
		viaggiSalvati.forEach(System.out::println);

		// Esempio di utilizzo di altre operazioni sui repository
		// ...
	}
}
