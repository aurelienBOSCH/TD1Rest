package com.inti.SpringRestTD1.controller;

import java.util.List;

import com.inti.SpringRestTD1.model.Professeur;
import com.inti.SpringRestTD1.model.Etudiant;
import com.inti.SpringRestTD1.repository.EcoleRepository;
import com.inti.SpringRestTD1.repository.EtudiantRepository;
import com.inti.SpringRestTD1.repository.ProfesseurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfesseurController
{

	@Autowired
	ProfesseurRepository professeurRepository;
	
	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	EcoleRepository ecoleRepository;
	
	@GetMapping("/listeProfs") // dans une application REST on a tout intéret à utiliser cette méthode
	public ResponseEntity<List<Professeur>> getAllProf()
	{
		return new ResponseEntity<List<Professeur>>(professeurRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping ("/saveProfesseur")
	public ResponseEntity<Professeur> saveProfessor(@RequestBody Professeur professeur)
	{
		return new ResponseEntity<Professeur>(professeurRepository.save(professeur), HttpStatus.CREATED);
	}
	
	

	@GetMapping ("/deleteProfesseur")
	public String deleteProfessor(@RequestParam(value="id")int id) 
	{
		 professeurRepository.deleteById(id);
	return "Le professeur a été supprimé";
	
	}
	
	@PutMapping("/updateProfesseur/{id}")
	public String updateProfesseur(@RequestBody Professeur professeur, @PathVariable int id)
	{
		Professeur p1 = professeurRepository.getReferenceById(id);
	if(!p1.getNom().equals(professeur.getNom()))
		{
			p1.setNom(professeur.getNom());
		}
	if(!p1.getPrenom().equals(professeur.getPrenom()) && professeur.getPrenom() != null )
	{
		p1.setPrenom(professeur.getPrenom());
	}
		professeurRepository.save(p1);
		
		return "Le professeur " + p1 + " a été mis à jour";
	}
}
