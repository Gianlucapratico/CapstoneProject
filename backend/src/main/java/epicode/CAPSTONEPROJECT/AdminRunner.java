package epicode.CAPSTONEPROJECT;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import epicode.CAPSTONEPROJECT.entities.Role;
import epicode.CAPSTONEPROJECT.entities.User;
import epicode.CAPSTONEPROJECT.repositories.RoleRepository;
import epicode.CAPSTONEPROJECT.repositories.UsersRepository;

@Component
public class AdminRunner implements CommandLineRunner {

	@Autowired
	UsersRepository usersRepo;

	// mi serve per impostare la password criptata
	@Autowired
	private PasswordEncoder bcrypt;

	// mi serve per recuperare il ruolo ADMINok
	@Autowired
	RoleRepository roleRepo;

	// attributi
	private static String nome;
	private static String cognome;
	private static String username;
	private static String email;
	private static String password;

	// recupero i dati del superAdmin dal mio file privato
	@Value("${user.admin.nome}")
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Value("${user.admin.cognome}")
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Value("${user.admin.username}")
	public void setUsername(String username) {
		this.username = username;
	}

	@Value("${user.admin.email}")
	public void setEmail(String email) {
		this.email = email;
	}

	@Value("${user.admin.password}")
	public void setPassword(String password) {
		this.password = password;
	}

	// TODO: metodo che istanzia UserCreatePayload, imposta la password criptata e
	// gli assegna "ADMIN" come ruolo
	public void createUserAdmin() {

		if (!usersRepo.findByEmail(email).isPresent()) {

			User userAdmin = new User(nome, cognome, username, email, password);
			userAdmin.setPassword(bcrypt.encode(password));

			Optional<Role> roleAdmin = roleRepo.findByNome("ADMIN");
			userAdmin.getRoles().add(roleAdmin.get()); // => forse un if che controlla se non è vuoto o altro test?

			usersRepo.save(userAdmin);
		}

	}

	@Override
	public void run(String... args) throws Exception {

		createUserAdmin();
	}

}
