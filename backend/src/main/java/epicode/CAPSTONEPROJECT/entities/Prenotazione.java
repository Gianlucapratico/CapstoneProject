package epicode.CAPSTONEPROJECT.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prenotazione {
	@Id
	@GeneratedValue
	private UUID id;
	private LocalDate dataPrenotazione;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "viaggio_id")
	private Viaggio viaggio;

	@OneToMany(mappedBy = "prenotazione")
	private List<Recensione> recensione;

	public Prenotazione(Viaggio viaggio, LocalDate dataPrenotazione, User user, LocalDate dataArrivo) {
		this.viaggio = viaggio;
		this.dataPrenotazione = dataPrenotazione;
		this.user = user;

	}
}
