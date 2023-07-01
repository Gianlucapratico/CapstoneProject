package epicode.CAPSTONEPROJECT;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import epicode.CAPSTONEPROJECT.entities.Cliente;
import epicode.CAPSTONEPROJECT.repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(3)
public class ClienteRunner implements CommandLineRunner {
	@Autowired
	ClienteRepository clienteRepo;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		Random random = new Random();

		List<Cliente> clienteDb = clienteRepo.findAll();

		if (clienteDb.size() == 0) {
			for (int i = 0; i < 20; i++) {
				try {

					String nome = faker.name().firstName();
					Integer partitaIva = faker.number().numberBetween(111111, 999999);

					String email = faker.internet().emailAddress();
					String telefono = faker.phoneNumber().cellPhone();
					String emailContatto = faker.internet().emailAddress();
					String nomeContatto = faker.name().firstName();
					String cognome = faker.name().lastName();

					// Double fatturatoAnnuo = Cliente.NUOVOio

					Cliente newcliente = new Cliente(nome, email, telefono, cognome);

					// newcliente.fatturatoAnnuo(newcliente.getFatture());
					clienteRepo.save(newcliente);

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

	}

}
