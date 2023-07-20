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
			Viaggio roma = new Viaggio("Roma", "Italia", LocalDate.of(2023, 8, 5), LocalDate.of(2022, 10, 10),
					"Esplora la Città Eterna: antichi tesori storici, arte magnifica e cibo delizioso. Roma ti accoglie con il suo fascino senza tempo e il cuore caloroso dell'Italia. 🏛️🍕✨ #Roma #ViaggioIndimenticabile",
					250.0, "/immagini/roma.jpg");
			viaggiorepo.save(roma);

			Viaggio parigi = new Viaggio("Parigi", "Francia", LocalDate.of(2023, 10, 1), LocalDate.of(2022, 10, 10),
					"Vivi la magia di Parigi: torre Eiffel, romanticismo senza pari, arte, moda e caffè incantevoli. La Ville Lumière ti catturerà con il suo splendore senza tempo. 🗼❤️🇫🇷 #Parigi #ViaggioIndimenticabile",
					300.0, "/immagini/parigi.jpg");
			viaggiorepo.save(parigi);

			Viaggio rc = new Viaggio("Reggio Calabria", "Italia", LocalDate.of(2023, 8, 10), LocalDate.of(2022, 10, 10),
					"Scopri la bellezza di Reggio Calabria: mare cristallino, storia millenaria, cultura autentica e cucina deliziosa. Un paradiso nel cuore della Calabria ti aspetta! 🏖️🏛️🍝 #ReggioCalabria #ViaggioIndimenticabile",
					150.0, "/immagini/RC.jpg");
			viaggiorepo.save(rc);

			Viaggio budapest = new Viaggio("Budapest", "Ungheria", LocalDate.of(2023, 10, 15),
					LocalDate.of(2022, 10, 10),
					"Esplora Budapest: Bagni termali incantevoli, architettura imponente, cultura vibrante e cibo delizioso. Un'esperienza unica ti attende nella perla del Danubio! 🛀🏰🍲 #Budapest #ViaggioIndimenticabile",
					300.0, "/immagini/Budapest.jpg");
			viaggiorepo.save(budapest);

			Viaggio barcelona = new Viaggio("Barcelona", "Spagna", LocalDate.of(2023, 9, 11),
					LocalDate.of(2022, 10, 10),
					"Scopri l'incanto di Barcellona: architettura unica, vibe mediterranea, cibo delizioso e arte senza pari. Un viaggio indimenticabile ti aspetta! 🌟🇪🇸 #Barcelona #ViaggioMagico",
					200.0, "/immagini/Barcelona.jpg");
			viaggiorepo.save(barcelona);

			Viaggio lasvegas = new Viaggio("Las Vegas", "USA", LocalDate.of(2023, 12, 25), LocalDate.of(2022, 10, 10),
					"Benvenuto a Las Vegas, la città del divertimento e delle luci sfavillanti! Gioca d'azzardo nei casinò, assisti a spettacoli mozzafiato, e vivi il lusso in questa destinazione unica. 🎰✨🌆 #LasVegas #ViaggioIndimenticabile",
					1000.0, "/immagini/lasvegas.jpg");
			viaggiorepo.save(lasvegas);
		}

	}
}
