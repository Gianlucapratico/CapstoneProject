package epicode.CAPSTONEPROJECT.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Destinazione {
	@Id
	@GeneratedValue
	private UUID id;

	@Column(name = "citta")
	private String citta;

	@Column(name = "stato")
	private String stato;
	@JsonIgnore
	@OneToMany(mappedBy = "destinazione")
	private List<Prenotazione> prenotazioni;

	public Destinazione(String citta, String stato) {
		this.citta = citta;
		this.stato = stato;
	}
}
