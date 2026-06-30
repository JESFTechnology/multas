package com.ifsuldeminas.edu.br.multas.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifsuldeminas.edu.br.multas.model.entities.Police;

@Repository
public interface PoliceRepository extends JpaRepository<Police, Integer> {

}