//package epicode.CAPSTONEPROJECT;
//
//import java.util.List;
//import java.util.Locale;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.github.javafaker.Faker;
//
//import epicode.CAPSTONEPROJECT.entities.Cliente;
//import epicode.CAPSTONEPROJECT.repositories.ClienteRepository;
//
//@Order(1)
//@Component
//public class ClienteRunner implements CommandLineRunner {
//	@Autowired
//	ClienteRepository clienteRepo;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Faker faker = new Faker(new Locale("it"));
//
//		List<Cliente> clienteDb = clienteRepo.findAll();
//
//		if (clienteDb.size() == 0) {
//			for (int i = 0; i < 20; i++) {
//				try {
//
//					String nome = faker.name().firstName();
//					String email = faker.internet().emailAddress();
//					String telefono = faker.phoneNumber().cellPhone();
//					String cognome = faker.name().lastName();
//					Cliente newcliente = new Cliente(nome, telefono, email, cognome);
//
//					clienteRepo.save(newcliente);
//
//				} catch (Exception e) {
//					System.out.println(e);
//				}
//			}
//		}
//
//	}
//
//}
