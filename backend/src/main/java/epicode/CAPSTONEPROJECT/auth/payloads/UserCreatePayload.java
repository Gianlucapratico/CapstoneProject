package epicode.CAPSTONEPROJECT.auth.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatePayload {
	@NotBlank(message = "Il nome è obbligatorio")
	@Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
	private String nome;

	@NotBlank(message = "Il cognome è obbligatorio")
	@Size(min = 3, max = 30, message = "Cognome min 3 caratteri, massimo 30")
	private String cognome;

	@NotBlank(message = "Lo username è obbligatorio")
	@Size(min = 3, max = 30, message = "Username min 3 caratteri, massimo 30")
	private String username;

	@NotBlank(message = "L'email è obbligatoria")
	@Email(message = "Non hai inserito un indirizzo email valido")
	private String email;

	@NotBlank(message = "La password è obbligatoria")
	@Size(min = 5, message = "La password deve avere almeno 5 caratteri")
	private String password;

	public UserCreatePayload(String nome, String cognome, String username, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
