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
	private LocalDate dataPartenza;
	private LocalDate dataArrivo;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "destinazione_id")
	private Destinazione destinazione;

	@OneToOne(mappedBy = "prenotazione")
	private Recensione recensione;

	public Prenotazione(Destinazione destinazione, LocalDate dataPrenotazione, Cliente cliente, LocalDate dataPartenza,
			LocalDate dataArrivo, Recensione recensione) {
		this.destinazione = destinazione;
		this.dataPrenotazione = dataPrenotazione;
		this.cliente = cliente;
		this.dataPartenza = dataPartenza;
		this.dataArrivo = dataArrivo;
		this.recensione = recensione;
	}

	public Prenotazione(Destinazione destinazione, LocalDate dataPrenotazione, Cliente cliente, LocalDate dataPartenza,
			LocalDate dataArrivo) {
		this.destinazione = destinazione;
		this.dataPrenotazione = dataPrenotazione;
		this.cliente = cliente;
		this.dataPartenza = dataPartenza;
		this.dataArrivo = dataArrivo;

	}
}
