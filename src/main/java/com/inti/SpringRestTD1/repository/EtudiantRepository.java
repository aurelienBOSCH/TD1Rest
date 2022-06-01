package com.inti.SpringRestTD1.repository;

import java.util.List;

import com.inti.SpringRestTD1.model.Etudiant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer>
{
	@Query(value="Select * from etudiant where id_ecole=:id_ecole", nativeQuery = true)
	List<Etudiant> findAllStudentsInSchool(@Param("id_ecole") int id_ecole);
	
	@Query(value="Select * from etudiant etu, ecole eco  where eco.id=etu.id_ecole and eco.ville=:ville", nativeQuery = true)
	List<Etudiant> findAllStudentsInCity (@Param("ville") String ville);
}
