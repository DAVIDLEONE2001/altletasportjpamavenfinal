package it.altletasportjpamaven.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "atleta")
public class Atleta {

	//(id, nome, cognome, dataDiNascita, codice, numeroMedaglieVinte, sports)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "codice")
	private String codice;
	@Column(name = "numeromedaglievinte")
	private int numeroMedaglieVinte;
	@Column(name = "datadiascita")
	private LocalDate dataDiNascita; 
	
	//TODO enum
	
	@ManyToMany
	@JoinTable(name = "atleta_sport", joinColumns = @JoinColumn(name = "atleta_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name= "sport_id", referencedColumnName = "ID"))
	List<Sport> sports = new ArrayList<>();
	
	
	

	public Atleta(String nome, String cognome, String codice, int numeroMedaglieVinte, LocalDate dataDiNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codice = codice;
		this.numeroMedaglieVinte = numeroMedaglieVinte;
		this.dataDiNascita = dataDiNascita;
	}
	
	public Atleta(String nome, String cognome, String codice, int numeroMedaglieVinte, List<Sport> sports) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codice = codice;
		this.numeroMedaglieVinte = numeroMedaglieVinte;
		this.sports = sports;
	}
	public Atleta(Long id, String nome, String cognome, String codice, int numeroMedaglieVinte) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codice = codice;
		this.numeroMedaglieVinte = numeroMedaglieVinte;
	}
	public Atleta() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public int getNumeroMedaglieVinte() {
		return numeroMedaglieVinte;
	}
	public void setNumeroMedaglieVinte(int numeroMedaglieVinte) {
		this.numeroMedaglieVinte = numeroMedaglieVinte;
	}
	public List<Sport> getSports() {
		return sports;
	}
	public void setSports(List<Sport> sports) {
		this.sports = sports;
	}
	
	
	
}
