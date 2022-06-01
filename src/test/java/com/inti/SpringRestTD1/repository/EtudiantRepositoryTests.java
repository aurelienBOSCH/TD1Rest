package com.inti.SpringRestTD1.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.inti.SpringRestTD1.model.Etudiant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = Replace.NONE) // pour préciser qu'on va utiliser hibernate et non pas la bdd intégrée
@DataJpaTest
public class EtudiantRepositoryTests
{
	@Autowired
	EtudiantRepository etudiantRepository;
	
	Etudiant etudiant0, etudiant1, etudiant2;
	
	@BeforeEach
	public void setUp()
	{
		etudiant0 = new Etudiant();
		etudiant1 = new Etudiant("Zidane", "Zinedine", "zizou@gmail.com", 5);
		etudiant2 = etudiantRepository.save(etudiant1);
	}
	
	@Test
	public void testSaveStudent()
	{
		//Given
			//déjà dans le setUp()
		//When
		Etudiant savedStudent = etudiantRepository.save(etudiant1);
		//Then
		assertThat(savedStudent).isNotNull();
		assertThat(savedStudent.getId()).isGreaterThan(0); //pas vraiment nécessaire
	}
	
	@Test
	public void testGetEtudiant()
	{
		//When  (recupère en base)
		Etudiant toGetEtudiant = etudiantRepository.getReferenceById(etudiant2.getId());
		
		//Then
		assertThat(toGetEtudiant).isNotNull();
		assertThat(toGetEtudiant.getId()).isEqualTo(etudiant2.getId());
		assertThat(toGetEtudiant.getNom()).isEqualTo("Zidane");
		assertThat(toGetEtudiant.getPrenom()).isEqualTo("Zinedine");
		assertThat(toGetEtudiant.getEmail()).isEqualTo("zizou@gmail.com");
		assertThat(toGetEtudiant.getAnneeEtude()).isEqualTo(5);
	}
	
	@Test
	public void testUpdateEtudiant()
	{
		//Given
		Etudiant e1 = etudiantRepository.save(etudiant1);
		//When
		e1.setNom("Ronaldo");
		e1.setPrenom("Cristiano");
		Etudiant e2 = etudiantRepository.save(e1);
		//Then
		assertThat(e2).isNotNull();
		assertThat(e2.getNom()).isEqualTo("Ronaldo");
	
	}
	
	@Test
	public void testGetAllStudents()
	{
		//When
		List<Etudiant> listeEtudiants = etudiantRepository.findAll();
		//Then
		assertThat(listeEtudiants).isNotEmpty();
		assertThat(listeEtudiants).hasSize((int) etudiantRepository.count());
	}
	
	@Test
	public void testGetAllStudentsInCity()
	{
		//When
		List<Etudiant> listeEtudiants = etudiantRepository.findAllStudentsInCity("Versailles");
		//Then
		assertThat(listeEtudiants).isNotEmpty();
		assertThat(listeEtudiants).hasSize((int) etudiantRepository.count());
		assertThat(listeEtudiants.get(0).getEcole().getVille()).isEqualTo("Versailles");
		
	}
}
