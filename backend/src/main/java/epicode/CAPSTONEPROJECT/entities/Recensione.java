package epicode.CAPSTONEPROJECT.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recensione {
	@Id
	@GeneratedValue
	private UUID id;

	private String commento;
	private int valutazione;
//	@OneToOne
//	@JoinColumn(name = "prenotazione_id")
//	private Prenotazione prenotazione;
	@OneToOne
	@JoinColumn(name = "prenotazione_id")
	private Prenotazione prenotazione;

	public Recensione(Prenotazione prenotazione, String commento, int valutazione) {
		this.prenotazione = prenotazione;
		this.commento = commento;
		this.valutazione = valutazione;
	}
}
