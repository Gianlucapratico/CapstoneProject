package epicode.CAPSTONEPROJECT.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Viaggio {
	@Id
	@GeneratedValue
	private UUID id;

	private LocalDate dataPartenza;
	private LocalDate dataArrivo;

	@ManyToOne
	@JsonManagedReference
	private Comune comune;

	public Viaggio(LocalDate dataPartenza, LocalDate dataArrivo, Comune comune) {

		this.dataPartenza = dataPartenza;
		this.dataArrivo = dataArrivo;
		this.comune = comune;
	}
}
