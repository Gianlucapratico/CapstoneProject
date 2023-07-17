package epicode.CAPSTONEPROJECT.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

	@OneToOne(mappedBy = "prenotazione")
	private Recensione recensione;

	public Prenotazione(Viaggio viaggio, LocalDate dataPrenotazione, User user, Recensione recensione) {
		this.viaggio = viaggio;
		this.dataPrenotazione = dataPrenotazione;
		this.user = user;
		this.recensione = recensione;
	}

	public Prenotazione(Viaggio viaggio, LocalDate dataPrenotazione, User user, LocalDate dataArrivo) {
		this.viaggio = viaggio;
		this.dataPrenotazione = dataPrenotazione;
		this.user = user;

	}
}
