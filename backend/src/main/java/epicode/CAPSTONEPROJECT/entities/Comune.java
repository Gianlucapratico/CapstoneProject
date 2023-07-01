package epicode.CAPSTONEPROJECT.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.opencsv.bean.CsvBindByName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comuni")
@Getter
@Setter
@NoArgsConstructor
public class Comune {
	@Id
	@GeneratedValue
	private UUID id;

	@CsvBindByName(column = "Codice Provincia")
	private String codiceProvincia;

	@CsvBindByName(column = "Progressivo del Comune")
	private String progressivoDelComune;

	@CsvBindByName(column = "Denominazione in Italiano")
	private String denominazioneInItaliano;

	@CsvBindByName(column = "Nome Provincia")
	private String nomeProvincia;

	@ManyToOne
	@JoinColumn(name = "provincia_id")
	@JsonBackReference
	private Provincia provincia;

	@OneToMany(mappedBy = "comune", cascade = CascadeType.ALL)
	private List<Prenotazione> prenotazioni;

	public Comune(String codiceProvincia, String progressivoDelComune, String denominazioneInItaliano,
			String nomeProvincia, Provincia provincia) {
		this.codiceProvincia = codiceProvincia;
		this.progressivoDelComune = progressivoDelComune;
		this.denominazioneInItaliano = denominazioneInItaliano;
		this.nomeProvincia = nomeProvincia;
		this.provincia = provincia;
		this.prenotazioni = new ArrayList<>();
	}

	// Rimuovi la annotazione @ToString per evitare la ricorsione infinita
	// Implementa un metodo toString() personalizzato
	@Override
	public String toString() {
		return "Comune{" + "id=" + id + ", codiceProvincia='" + codiceProvincia + '\'' + ", progressivoDelComune='"
				+ progressivoDelComune + '\'' + ", denominazioneInItaliano='" + denominazioneInItaliano + '\''
				+ ", nomeProvincia='" + nomeProvincia + '\'' + '}';
	}
}
