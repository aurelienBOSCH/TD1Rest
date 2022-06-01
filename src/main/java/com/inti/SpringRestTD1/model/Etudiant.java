package com.inti.SpringRestTD1.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Etudiant
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private @NonNull String nom;
	@Column(nullable = false)
	private @NonNull String prenom;
	private String email;
	private String telephone;
	private int anneeEtude;

	@ManyToMany
	@JoinTable(name = "prof_etudiant", joinColumns = @JoinColumn(name = "id_etu"), inverseJoinColumns = @JoinColumn(name = "id_prof"))
	@JsonIgnore
	private List<Professeur> listeProfesseurs;

	@ManyToOne
	@JoinColumn(name = "id_ecole")
	@JsonIgnore
	private Ecole ecole;

	public Etudiant(@NonNull String nom, @NonNull String prenom, int anneeEtude)
	{
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.anneeEtude = anneeEtude;
	}

	public Etudiant(@NonNull String nom, @NonNull String prenom, String email, int anneeEtude)
	{
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.anneeEtude = anneeEtude;
	}


	
	

}
