package epicode.CAPSTONEPROJECT.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente {
	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private String telefono;
	private String cognome;
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Prenotazione> prenotazioni;

	public Cliente(String nome, String telefono, String email, String cognome) {
		this.nome = nome;
		this.telefono = telefono;
		this.email = email;
		this.cognome = cognome;
	}
}
