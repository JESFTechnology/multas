package com.ifsuldeminas.edu.br.multas.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifsuldeminas.edu.br.multas.model.entities.Penalty;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalty, Integer> {

}