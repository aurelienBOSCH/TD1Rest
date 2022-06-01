package com.inti.SpringRestTD1.repository;

import java.util.List;

import com.inti.SpringRestTD1.model.Ecole;
import com.inti.SpringRestTD1.model.Etudiant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EcoleRepository extends JpaRepository<Ecole, Integer>
{
	@Query(value="Select eco.* from etudiant etu, ecole eco  where eco.id=etu.id_ecole and etu.email=:email", nativeQuery = true)
	Ecole findStudentShoolByHisEmail (@Param("email") String email);
	
	@Query(value="Select eco.* from etudiant etu, ecole eco  where eco.id=etu.id_ecole and etu.nom=:nom and etu.prenom=:prenom", nativeQuery = true)
	Ecole findStudentShoolByHisLastNameAndFirstName (@Param("nom") String nom, @Param("prenom") String prenom);
}
