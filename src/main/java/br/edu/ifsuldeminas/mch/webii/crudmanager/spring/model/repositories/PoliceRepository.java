package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Police;

@Repository
public interface PoliceRepository extends JpaRepository<Police, Integer> {
	
	// ChatGPT recomendou o Optional porque: 
	// Para evitar que quando não encontre o email do policial ele 
	// acabe enviando NullPointerException
	
    Optional<Police> findByEmail(String email);
}