package epicode.CAPSTONEPROJECT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import epicode.CAPSTONEPROJECT.entities.Comune;
import epicode.CAPSTONEPROJECT.entities.Provincia;
import epicode.CAPSTONEPROJECT.repositories.ComuneRepository;
import epicode.CAPSTONEPROJECT.repositories.ProvinciaRepository;
import epicode.CAPSTONEPROJECT.services.ComuneService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(1)
public class ComuneRunner implements CommandLineRunner {

	@Autowired
	ComuneService comuneService;
	@Autowired
	ProvinciaRepository provinciaRepo;
	@Autowired
	ComuneRepository comuneRepo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		// OK

		// try {
		// CsvToBeanBuilder<Comune> beanBuilder = new CsvToBeanBuilder<>(
		// new InputStreamReader(new FileInputStream("comuni-italiani.csv")));
		//
		// beanBuilder.withType(Comune.class);
		// // build methods returns a list of Beans
		// beanBuilder.build().parse().forEach(e -> log.error(e.toString()));
		//
		// } catch (FileNotFoundException e) {
		// log.error(e.getMessage(), e);
		// }
		// }
		// *******************************

		// // String csvFile = "classpath:static/comuni-italiani.csv";
		// String csvFile = "comuni-italiani.csv";
		//
		// // List<Comune> beans = new CsvToBeanBuilder(new
		// FileReader("classpath:static/comuni-italiani.csv"))
		// // .withType(Comune.class).build().parse();
		//
		// ColumnPositionMappingStrategy<Comune> mappingStrategy = new
		// ColumnPositionMappingStrategy<>();
		// mappingStrategy.setType(Comune.class);
		//
		// mappingStrategy.setColumnMapping("CODICE PROVINCIA", "PROGRESSIVO DEL
		// COMUNE", "DENOMINAZIONE IN ITALIANO");
		//
		// try (FileReader fileReader = new FileReader(csvFile)) {
		// // CsvToBean<Comune> csvToBean = new
		// CsvToBeanBuilder<Comune>(fileReader).withType(Comune.class)
		// // .withIgnoreLeadingWhiteSpace(true).build();
		//
		// CsvToBean<Comune> csvToBean = new
		// CsvToBeanBuilder<Comune>(fileReader).withType(Comune.class)
		// .withMappingStrategy(new HeaderColumnNameMappingStrategy<Comune>()).build();
		//
		// List<Comune> comuni = csvToBean.parse();
		//
		// //Process the dataList as needed
		// for (Comune comune : comuni) {
		// System.out.println(comune);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }

		// List <Comune> beans = new CsvToBeanBuilder<Comune>(new FileReader(csvFile))
		// .withType(Comune.class).build().parse();

		// beans.add(null);

		if (comuneRepo.findAll().size() == 0) {

			String filePath = new File("comuni-italiani.csv").getAbsolutePath();
			boolean isFirstLine = true;

			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String line;
				while ((line = br.readLine()) != null) {
					if (isFirstLine) {
						isFirstLine = false;
						continue;
					}

					String[] columns = line.split(";");
					String codiceProvincia = columns[0];
					String progressivoComune = columns[1];
					String denominazione = columns[2];
					String nomeProvincia = columns[3];

					System.out.println("Codice provincia: " + codiceProvincia + ", progressivo del comune: " + progressivoComune
							+ ", denominazione in italiano: " + denominazione + ", nome provincia: " + nomeProvincia);
					// Comune newComune = new Comune(codiceProvincia, progressivoComune,
					// denominazione, nomeProvincia);
					Provincia provincia = provinciaRepo.findByNome(nomeProvincia);
					if (provincia != null) {
						// List<Comune> comuni = new ArrayList<>();
						Comune newComune = new Comune(codiceProvincia, progressivoComune, denominazione, nomeProvincia, provincia);
						// List<Comune> comuniConProvincia =
						// comuneService.createComuniWithProvincia(comuni);
						comuneRepo.save(newComune);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
