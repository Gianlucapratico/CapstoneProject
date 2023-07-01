package epicode.CAPSTONEPROJECT.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate dataPrenotazione;
	private String nomePasseggero;
	@ManyToOne
	@JoinColumn(name = "comune_id")
	private Comune comune;

	@OneToOne(mappedBy = "prenotazione")
	private Recensione recensione;

	public Prenotazione(Long id, Comune comune, LocalDate dataPrenotazione, String nomePasseggero) {
		super();
		this.id = id;
		this.comune = comune;
		this.dataPrenotazione = dataPrenotazione;
		this.nomePasseggero = nomePasseggero;
	}

	// Costruttori, getter e setter, ecc.
}