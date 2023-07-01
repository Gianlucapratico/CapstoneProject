package epicode.CAPSTONEPROJECT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import epicode.CAPSTONEPROJECT.entities.Comune;
import epicode.CAPSTONEPROJECT.entities.Provincia;
import epicode.CAPSTONEPROJECT.repositories.ComuneRepository;
import epicode.CAPSTONEPROJECT.repositories.ProvinciaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(0)
public class ProvinciaRunner implements CommandLineRunner {
	@Autowired
	ProvinciaRepository provinciaRepo;
	@Autowired
	ComuneRepository comuneRepo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stubciao

		if (provinciaRepo.findAll().size() == 0) {

			String filePath = new File("province-italiane.csv").getAbsolutePath();
			boolean isFirstLine = true;

			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = br.readLine()) != null) {
					if (isFirstLine) {
						isFirstLine = false;
						continue;
					}

					String[] columns = line.split(";");
					String siglaProvincia = columns[0];
					String provincia = columns[1];
					String regione = columns[2];

					System.out.println(
							"Sigla: " + siglaProvincia + ", provincia: " + provincia + ", Regione: " + regione);
					List<Comune> comuni = comuneRepo.findAll();
					List<Comune> comuniPerProvincia = new ArrayList();
					for (Comune comune : comuni) {
						if (comune.getNomeProvincia().equals(provincia)) {
							comuniPerProvincia.add(comune);

						}

					}
					Provincia provincia1 = new Provincia();
					provincia1.setSigla(siglaProvincia);
					provincia1.setNome(provincia);
					provincia1.setRegione(regione);
					provincia1.setComuni(comuniPerProvincia);
					provinciaRepo.save(provincia1);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
