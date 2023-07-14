package epicode.CAPSTONEPROJECT.entities;

import java.time.LocalDate;
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
public class Viaggio {
	@Id
	@GeneratedValue
	private UUID id;
	private LocalDate dataPartenza;
	private LocalDate dataArrivo;
	private String descrizione;
	private Double prezzo;
	private String urlImg;

	@Column(name = "citta")
	private String citta;

	@Column(name = "stato")
	private String stato;
	@JsonIgnore
	@OneToMany(mappedBy = "viaggio")
	private List<Prenotazione> prenotazioni;

	public Viaggio(String citta, String stato, LocalDate dataPartenza, LocalDate dataArrivo, String descrizione,
			Double prezzo, String urlImg) {
		this.citta = citta;
		this.stato = stato;
		this.dataPartenza = dataPartenza;
		this.dataArrivo = dataArrivo;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.urlImg = urlImg;
	}
}
