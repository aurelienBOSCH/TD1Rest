package com.inti.SpringRestTD1.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
public class Ecole
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column (nullable = false)
	private @NonNull String nom;
	private String adresse;
	private int cp;
	private String ville;
	
	@ManyToMany
	@JoinTable(name = "prof_ecole",
				joinColumns = @JoinColumn(name = "id_ecole"),
				inverseJoinColumns = @JoinColumn(name = "id_prof"))
	@JsonIgnore
	private List<Professeur> listeProfesseurs;
	
	
	@OneToMany(mappedBy = "ecole"/*,
				cascade = CascadeType.ALL*/)
	@JsonIgnore //pour eviter de faire des boucles de toString infinis
	private List<Etudiant> listeEtudiants;


//	@Override
//	public String toString()
//	{
//		return "Ecole [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", cp=" + cp + ", ville=" + ville + "]";
//	}
	
	
	
}
