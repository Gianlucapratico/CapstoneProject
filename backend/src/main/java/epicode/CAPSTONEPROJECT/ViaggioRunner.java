package epicode.CAPSTONEPROJECT;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import epicode.CAPSTONEPROJECT.entities.Viaggio;
import epicode.CAPSTONEPROJECT.repositories.ViaggioRepository;

@Order(2)
@Component
public class ViaggioRunner implements CommandLineRunner {
	@Autowired
	ViaggioRepository viaggiorepo;

	@Override
	public void run(String... args) {
		if (viaggiorepo.count() == 0) {
			Viaggio roma = new Viaggio("Roma", "Italia", LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 10),
					"Descrizione del viaggio", 250.0);
			viaggiorepo.save(roma);

			Viaggio parigi = new Viaggio("Parigi", "Francia", LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 10),
					"Descrizione del viaggio", 300.0);
			viaggiorepo.save(parigi);

			Viaggio rc = new Viaggio("Reggio Calabria", "Italia", LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 10),
					"Descrizione del viaggio", 150.0);
			viaggiorepo.save(rc);

			Viaggio budapest = new Viaggio("Budapest", "Ungheria", LocalDate.of(2022, 10, 1),
					LocalDate.of(2022, 10, 10), "Descrizione del viaggio", 300.0);
			viaggiorepo.save(budapest);

			Viaggio barcelona = new Viaggio("Barcelona", "Spagna", LocalDate.of(2022, 10, 1),
					LocalDate.of(2022, 10, 10), "Descrizione del viaggio", 200.0);
			viaggiorepo.save(barcelona);

			Viaggio lasvegas = new Viaggio("Las Vegas", "USA", LocalDate.of(2022, 10, 1), LocalDate.of(2022, 10, 10),
					"Descrizione del viaggio", 1000.0);
			viaggiorepo.save(lasvegas);
		}
//		Faker faker = new Faker();
//
//		if (destinazionerepo.count() == 0) {
//			for (int i = 0; i < 20; i++) {
//				try {
//					String citta = faker.address().city();
//					String stato = faker.address().country();
//					Viaggio destinazione = new Viaggio(citta, stato);
//					destinazionerepo.save(destinazione);
//				} catch (Exception e) {
//					System.out.println(e);
//				}
//			}
//		}
	}
}
