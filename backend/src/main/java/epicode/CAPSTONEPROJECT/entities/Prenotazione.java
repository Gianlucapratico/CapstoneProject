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
	private String nomePasseggero;

	@ManyToOne
	@JoinColumn(name = "comune_id")
	private Comune comune;

//	@OneToOne(mappedBy = "prenotazione")
//	private Recensione recensione;
	@OneToOne(mappedBy = "prenotazione")
	private Recensione recensione;

	public Prenotazione(Comune comune, LocalDate dataPrenotazione, String nomePasseggero) {
		this.comune = comune;
		this.dataPrenotazione = dataPrenotazione;
		this.nomePasseggero = nomePasseggero;
	}

	public Prenotazione(LocalDate dataPrenotazione, String nomePasseggero) {
		this.dataPrenotazione = dataPrenotazione;
		this.nomePasseggero = nomePasseggero;
	}

}
