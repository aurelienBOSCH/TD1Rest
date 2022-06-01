package com.inti.SpringRestTD1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inti.SpringRestTD1.Json;
import com.inti.SpringRestTD1.model.Ecole;
import com.inti.SpringRestTD1.model.Etudiant;
import com.inti.SpringRestTD1.repository.EcoleRepository;
import com.inti.SpringRestTD1.repository.EtudiantRepository;
import com.inti.SpringRestTD1.repository.ProfesseurRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EtudiantController.class)
public class EcoleControllerTests
{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EtudiantRepository etudiantRepository;
	
	@MockBean
	private ProfesseurRepository professeurRepository;
	
	@MockBean
	private EcoleRepository ecoleRepository;

	@Test
	@DisplayName("Test affichage de toutes les écoles")
	public void testAffichageAllSchools() 
	{
	
		try
		{
			mockMvc.perform(get("/listeEcoles"))
			.andExpect(status().isOk());

		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSaveSchool()
	{
		try
		{
			mockMvc.perform(post("/saveEcole")
			.contentType(MediaType.APPLICATION_JSON)
			.content(Json.fromObject(new Ecole("Ecole des zouaves", 44000, "Nantes"))))
			.andExpect(status().isCreated()); //conflict pour s'assurer qu'une erreur sera faites, OK pour le cas général ou c'est vérifié
		} 
		catch (JsonProcessingException e) // pour avoir un détail supplémentaire si on catche cette exception là
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAffichageSchoolByNomAndPrenom()
	{
		try
		{
			mockMvc.perform(get("/afficherEcoleEtudiant/{nom}/{prenom}" , "Cartman", "Eric"))
			.andExpect(status().isOk());

		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
}
