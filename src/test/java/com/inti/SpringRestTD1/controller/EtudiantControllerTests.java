package com.inti.SpringRestTD1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inti.SpringRestTD1.Json;
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
public class EtudiantControllerTests
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
	@DisplayName("Test affichage de tous les étudiants")
	public void testAffichageAllStudents() 
	{
	
		try
		{
			mockMvc.perform(get("/listeEtudiants"))
			.andExpect(status().isOk());

		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSaveStudent()
	{
		try
		{
			mockMvc.perform(post("/saveEtudiant")
			.contentType(MediaType.APPLICATION_JSON)
			.content(Json.fromObject(new Etudiant("Zidane", "Zinedine", "zizou@gmail.com", 5))))
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
	public void testAffichageAllStudentsInVersailles()
	{
		try
		{
			mockMvc.perform(get("/listeEtudiantVille/Versailles"))
			.andExpect(status().isOk());

		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAffichageAllStudentsInHoche()
	{
		try
		{
			mockMvc.perform(get("/listeEtudiantEcole/1"))
			.andExpect(status().isOk());

		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
