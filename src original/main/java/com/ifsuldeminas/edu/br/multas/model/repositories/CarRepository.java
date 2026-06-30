package com.ifsuldeminas.edu.br.multas.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifsuldeminas.edu.br.multas.model.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}