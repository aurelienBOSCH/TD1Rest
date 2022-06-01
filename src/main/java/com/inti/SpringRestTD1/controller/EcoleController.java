package com.inti.SpringRestTD1.controller;

import java.util.ArrayList;
import java.util.List;

import com.inti.SpringRestTD1.model.Ecole;
import com.inti.SpringRestTD1.model.Etudiant;
import com.inti.SpringRestTD1.model.Professeur;
import com.inti.SpringRestTD1.repository.EtudiantRepository;
import com.inti.SpringRestTD1.repository.ProfesseurRepository;
import com.inti.SpringRestTD1.repository.EcoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcoleController
{

	@Autowired
	EcoleRepository ecoleRepository;
	
	@Autowired
	ProfesseurRepository professeurRepository;
	
	@Autowired
	EtudiantRepository etudiantRepository;
	
	@GetMapping("/listeSchools") // dans une application REST on a tout intéret à utiliser cette méthode
	public ResponseEntity<List<Ecole>> getAllSchool()
	{
		return new ResponseEntity<List<Ecole>>(ecoleRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping ("/saveEcole")
	public ResponseEntity<Ecole> saveSchool(@RequestBody Ecole ecole)
	{
		return new ResponseEntity<Ecole>(ecoleRepository.save(ecole), HttpStatus.CREATED);
	}
	
	

	@DeleteMapping ("/deleteEcole")
	public String deleteSchool(@RequestParam("id")int id) 
	{
		 ecoleRepository.deleteById(id);
	return "L'école a été supprimé";
	
	}
	
	@PutMapping("/updateEcole/{id}")
	public String updateEcole(@RequestBody Ecole ecole, @PathVariable int id)
	{
		Ecole e1 = ecoleRepository.getReferenceById(id);
	if(!e1.getNom().equals(ecole.getNom()))
		{
			e1.setNom(ecole.getNom());
		}
	
		ecoleRepository.save(e1);
		
		return "L'école " + e1 + " a été mise à jour";
	}
	
	@PutMapping("/updateEcoleProfesseur/{idProf}/{idEcole}")
	public String updateSchoolWithProfessor(@PathVariable int idProf, @PathVariable int idEcole)
	{
		Ecole ecole = ecoleRepository.getReferenceById(idEcole);
		Professeur prof = professeurRepository.getReferenceById(idProf);
		List<Professeur> listeProfesseurs = new ArrayList<Professeur>();
		
		listeProfesseurs.add(prof);
		
		ecole.setListeProfesseurs(listeProfesseurs);
		ecoleRepository.save(ecole);
		
		return "L'école " + ecole.getNom() + " a été mis à jour"; 
		//on met getNom ici car sinon on fait une boucle, vu qu'on a pas de request body 
		//le jsonignore n'est pas pris en compte ici 
		
	}
	
	@GetMapping ("/afficherEcoleEtudiant/{nom}/{prenom}")
	public ResponseEntity<Ecole> findStudentShoolByHisLastNameAndFirstName(@PathVariable String nom, @PathVariable String prenom )
	{
		return new ResponseEntity<Ecole>(ecoleRepository.findStudentShoolByHisLastNameAndFirstName(nom, prenom), HttpStatus.OK);
	}
	
	@GetMapping ("/afficherEcoleEtudiant/{email}")
	public ResponseEntity<Ecole> findStudentShoolByHisEmail(@PathVariable String email)
	{
		return new ResponseEntity<Ecole>(ecoleRepository.findStudentShoolByHisEmail(email), HttpStatus.OK);
	}
	
	
	
}
