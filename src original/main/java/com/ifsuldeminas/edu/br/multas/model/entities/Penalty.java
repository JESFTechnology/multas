package com.ifsuldeminas.edu.br.multas.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "multas")
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    @Valid
    @NotNull(message = "Selecione um carro.")
    private Car car;

    @NotNull(message = "O valor da multa não pode ser vazio.")
    @DecimalMin(value = "0.01", message = "O valor da multa deve ser maior que zero.")
    private Double cost;

    @NotBlank(message = "O local da infração não pode ser vazio.")
    private String location;

    @ManyToOne(optional = false)
    @Valid
    @NotNull(message = "Selecione um policial.")
    private Police policeman;

    public Penalty() {
    }

    public Penalty(Integer id) {
        this.id = id;
        this.cost = 0.0;
        this.location = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Police getPoliceman() {
        return policeman;
    }

    public void setPoliceman(Police policeman) {
        this.policeman = policeman;
    }

}