package epicode.CAPSTONEPROJECT.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import epicode.CAPSTONEPROJECT.services.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UsersService usersService;

	@Autowired
	private PasswordEncoder bcrypt;

	// @Autowired
	// RoleRepository roleRepo;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Validated UserCreatePayload body) {

		body.setPassword(bcrypt.encode(body.getPassword()));

		User createdUser = usersService.create(body);

		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
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

			String token = JWTTools.createToken(user);
			return ResponseEntity.ok(new AuthenticationSuccessfullPayload(token));
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non trovato");
		} catch (UnauthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username o password errati");
		}
	}
}
