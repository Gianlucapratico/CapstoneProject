package epicode.CAPSTONEPROJECT.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "province")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Provincia {
	@Id
	@GeneratedValue
	private UUID id;

	@Column(name = "sigle")
	private String sigla;

	@Column(name = "provincia")
	private String nome;

	@Column(name = "regione")
	private String regione;

	@OneToMany(mappedBy = "provincia", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Comune> comuni;

	public Provincia(String sigla, String nome, String regione) {
		this.sigla = sigla;
		this.nome = nome;
		this.regione = regione;
		this.comuni = new ArrayList<>();
	}
}
