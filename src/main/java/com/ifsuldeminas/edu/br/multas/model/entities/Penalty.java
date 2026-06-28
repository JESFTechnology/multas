package com.ifsuldeminas.edu.br.multas.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "multas")
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    @Valid
    private Car car;

    @NotNull(message = "O valor da multa não pode ser vazio.")
    private int cost;

    @NotBlank(message = "O local da infração não pode ser vazio.")
    private String location;

    @ManyToOne(optional = false)
    @Valid
    private Police policeman;

    public Penalty() {}

    Penalty(Integer id) {
        this.id = id;
        setCost(0);
        setLocation("");
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
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