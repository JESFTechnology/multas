package com.ifsuldeminas.edu.br.multas.model.entities;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "carros")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "A placa não pode ser vazia.")
    @Length(min = 7, max = 7, message = "A placa deve conter 7 caracteres.")
    private String licensePlate;

    @NotBlank(message = "O CPF ou CNPJ não pode ser vazio.")
    private String cpfOrCnpj;

    @NotBlank(message = "O chassi não pode ser vazio.")
    private String chassis;

    @Min(value = 1900, message = "Ano inválido.")
    @Max(value = 2500, message = "Ano inválido.")
    private int year;

    public Car() {}

    Car(Integer id) {
        this.id = id;
        setLicensePlate("");
        setCpfOrCnpj("");
        setChassis("");
        setYear(0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}