package com.inti.SpringRestTD1.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inti.SpringRestTD1.model.Ecole;
import com.inti.SpringRestTD1.model.Etudiant;
import com.inti.SpringRestTD1.model.Professeur;
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
public class EtudiantController
{

	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	ProfesseurRepository professeurRepository;
	
	@Autowired
	EcoleRepository ecoleRepository;
	
	@GetMapping ("/test")
	public String hello()
	{
		return "Hello World!";
	}
	
	@GetMapping ("/listeEtudiants")
	public List<Etudiant> getAllStudents() 
	{
		return etudiantRepository.findAll();
	}
	
//	@PostMapping ("/saveEtudiant")
//	public String saveStudent(@RequestParam(value="nom")String nom, @RequestParam(value="prenom")String prenom ) 
//	{
//		Etudiant etu = new Etudiant(nom, prenom);
//		etudiantRepository.save(etu);
//		return "Etudiant " + etu + "a bien été ajouté";
//	}
	
	//METHODE EQUIVALENTE AVEC REST:
	
	@PostMapping ("/saveEtudiant")
	public ResponseEntity<Etudiant> saveStudent(@RequestBody Etudiant etudiant)
	{
		return new ResponseEntity<Etudiant>(etudiantRepository.save(etudiant), HttpStatus.CREATED);
	}
	
	

	@GetMapping ("/deleteEtudiant")
	public String deleteStudent(@RequestParam(value="id")int id) 
	{
		 etudiantRepository.deleteById(id);
	return "L'étudiant a été supprimé";
	
	}
	
	@PutMapping("/updateEtudiant/{id}") //put plutot pour les updates
	public String updateEtudiant(@RequestBody Etudiant etudiant, @PathVariable int id)
	{
		Etudiant e1 = etudiantRepository.getReferenceById(id);
	if(!e1.getNom().equals(etudiant.getNom()))
		{
			e1.setNom(etudiant.getNom());
		}
	if(!e1.getPrenom().equals(etudiant.getPrenom()) && etudiant.getPrenom() != null )
	{
		e1.setNom(etudiant.getPrenom());
	}
		etudiantRepository.save(e1);
		
		return "L'étudiant " + e1 + " a été mis à jour";
	}
	
	@GetMapping ("/listeEtudiantEcole/{idEcole}")
	public ResponseEntity<List<Etudiant>> listeEtudiantEcole(@PathVariable int idEcole)
	{
		return new ResponseEntity<List<Etudiant>>(etudiantRepository.findAllStudentsInSchool(idEcole), HttpStatus.OK);
	}
	
	@GetMapping ("/listeEtudiantVille/{ville}")
	public ResponseEntity<List<Etudiant>> listeEtudiantVille(@PathVariable String ville)
	{
		return new ResponseEntity<List<Etudiant>>(etudiantRepository.findAllStudentsInCity(ville), HttpStatus.OK);
	}
	
	
	
}
