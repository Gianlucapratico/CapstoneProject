package epicode.CAPSTONEPROJECT.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epicode.CAPSTONEPROJECT.auth.payloads.AuthenticationSuccessfullPayload;
import epicode.CAPSTONEPROJECT.entities.User;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.exceptions.UnauthorizedException;
import epicode.CAPSTONEPROJECT.payloads.UserCreatePayload;
import epicode.CAPSTONEPROJECT.payloads.UserLoginPayload;
import epicode.CAPSTONEPROJECT.repositories.UsersRepository;
import epicode.CAPSTONEPROJECT.services.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UsersService usersService;
	@Autowired
	private UsersRepository usersRepo;
	private JWTTools jwtTools;

	@Autowired
	private PasswordEncoder bcrypt;

	// @Autowired
	// RoleRepository roleRepo;
	private boolean isValidEmail(String email) {
		// Utilizza espressioni regolari per la validazione dell'indirizzo email
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return email.matches(emailRegex);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Validated UserCreatePayload body) {
		try {
			// Verifica se tutti i campi sono stati inseriti
			if (StringUtils.isEmpty(body.getNome()) || StringUtils.isEmpty(body.getNome())
					|| StringUtils.isEmpty(body.getUsername()) || StringUtils.isEmpty(body.getEmail())
					|| StringUtils.isEmpty(body.getPassword())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Devi inserire tutti i campi");
			}

			// Verifica la validità dell'indirizzo email
			if (!isValidEmail(body.getEmail())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email non valida");
			}

			// Verifica se l'indirizzo email è già in uso
			Optional<User> existingUser = usersRepo.findByEmail(body.getEmail());
			if (existingUser.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'indirizzo email è già in uso");
			}

			// Verifica la lunghezza del nome
			if (body.getNome().length() < 3) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Il nome deve avere almeno 3 caratteri");
			}
			// Verifica la lunghezza dello username
			if (body.getUsername().length() < 3) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lo username deve avere almeno 3 caratteri");
			}

			// Verifica la lunghezza della password
			if (body.getPassword().length() < 5) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La password deve avere almeno 5 caratteri");
			}

			// Aggiungi altre logiche di validazione se necessario

			body.setPassword(bcrypt.encode(body.getPassword()));
			User createdUser = usersService.create(body);

			return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Si è verificato un errore durante la registrazione");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginPayload body) {
		try {
			User user = usersService.findByUsername(body.getUsername());
			String plainPW = body.getPassword();
			String hashedPW = user.getPassword();

			if (!bcrypt.matches(plainPW, hashedPW)) {
				throw new UnauthorizedException("Credenziali non valide");
			}

			String token = jwtTools.createToken(user);
			return ResponseEntity.ok(new AuthenticationSuccessfullPayload(token));
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non trovato");
		} catch (UnauthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username o password errati");
		}
	}
}
