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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Table
@Entity
@Data
@AllArgsConstructor @NoArgsConstructor /*@RequiredArgsConstructor*/
public class Professeur
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String nom;
	@Column(nullable=false)
	private String prenom;
	private double salaire;
	
	@ManyToMany
	@JoinTable(name = "prof_etudiant",
				joinColumns = @JoinColumn(name = "id_prof"),
				inverseJoinColumns = @JoinColumn(name = "id_etu"))
	@JsonIgnore
	private List<Etudiant> listeEtudiants;
	
	@ManyToMany
	@JoinTable(name = "prof_ecole",
				joinColumns = @JoinColumn(name = "id_prof"),
				inverseJoinColumns = @JoinColumn(name = "id_ecole"))
	@JsonIgnore
	private List<Ecole> listeEcoles;
}
