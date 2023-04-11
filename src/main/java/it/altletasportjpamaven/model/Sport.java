package it.altletasportjpamaven.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sport")
public class Sport {
	
	//(id, descrizione, dataInizio, dataFine)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "definizione")
	private String definizione;
	@Column(name = "datainizio")
	private LocalDate dataInizio;
	@Column(name = "datafine")
	private LocalDate dataFine;
	
	
	
	public Sport(Long id, String definizione, LocalDate dataInizio, LocalDate dataFine) {
		super();
		this.id = id;
		this.definizione = definizione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}
	public Sport() {
		super();
	}
	public Sport(String definizione, LocalDate dataInizio, LocalDate dataFine) {
		super();
		this.definizione = definizione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDefinizione() {
		return definizione;
	}
	public void setDefinizione(String definizione) {
		this.definizione = definizione;
	}
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sport other = (Sport) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	
	
	
	

}
