package com.inti.SpringRestTD1.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.inti.SpringRestTD1.model.Ecole;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = Replace.NONE) // pour préciser qu'on va utiliser hibernate et non pas la bdd intégrée
@DataJpaTest
public class EcoleRepositoryTests2
{
	@Autowired
	EcoleRepository ecoleRepository;
	
	Ecole ecole0, ecole1, ecole2;
	
	@BeforeEach
	public void setUp()
	{
		ecole0 = new Ecole();
		ecole1 = new Ecole("Ecole des zouaves", 44000, "Nantes");
		ecole2 = ecoleRepository.save(ecole1);
	}
	
	@Test
	public void testSaveSchool()
	{
		//Given
			//déjà dans le setUp()
		//When
		Ecole savedSchool = ecoleRepository.save(ecole1);
		//Then
		assertThat(savedSchool).isNotNull();
		assertThat(savedSchool.getId()).isGreaterThan(0); //pas vraiment nécessaire
	}
	
	@Test
	public void testGetEcole()
	{
		//When  (recupère en base)
		Ecole toGetEcole = ecoleRepository.getReferenceById(ecole2.getId());
		
		//Then
		assertThat(toGetEcole).isNotNull();
		assertThat(toGetEcole.getId()).isEqualTo(ecole2.getId());
		assertThat(toGetEcole.getNom()).isEqualTo("Ecole des zouaves");
		assertThat(toGetEcole.getCp()).isEqualTo(44000);
		assertThat(toGetEcole.getVille()).isEqualTo("Nantes");
	}
	
	@Test
	public void testUpdateEcole()
	{
		//Given
		Ecole e1 = ecoleRepository.save(ecole1);
		//When
		e1.setNom("Ecole des Boss");
		Ecole e2 = ecoleRepository.save(e1);
		//Then
		assertThat(e2).isNotNull();
		assertThat(e2.getNom()).isEqualTo("Ecole des Boss");
	
	}
	
	@Test
	public void testGetAllSchools()
	{
		//When
		List<Ecole> listeEcoles = ecoleRepository.findAll();
		//Then
		assertThat(listeEcoles).isNotEmpty();
		assertThat(listeEcoles).hasSize((int) ecoleRepository.count());
	}
	
	@Test
	public void testfindStudentShoolByHisEmail()
	{
		//When
		Ecole ecole= ecoleRepository.findStudentShoolByHisEmail("rigolo@gmail.com");
		//Then
		assertThat(ecole).isNotNull();
	
	}
	
	@Test
	public void testfindStudentShoolByHisLastNameAndFirstName()
	{
		//When
		Ecole ecole= ecoleRepository.findStudentShoolByHisLastNameAndFirstName("Cartman","Eric");
		//Then
		assertThat(ecole).isNotNull();
	
	}
}
