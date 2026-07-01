package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Car;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Penalty;
import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Police;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalty, Integer> {
	boolean existsByCar(Car car);
	boolean existsByPoliceman(Police policeman);
}