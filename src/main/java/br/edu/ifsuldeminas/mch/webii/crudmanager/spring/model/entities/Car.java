package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
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

    @ManyToOne(optional = false)
    @Valid
    private User user;

    @NotBlank(message = "O chassi não pode ser vazio.")
    private String chassis;

    @Min(value = 1900, message = "Ano inválido.")
    @Max(value = 2500, message = "Ano inválido.")
    private int manufactureYear;

    public Car() {
    }

    public Car(Integer id) {
        this.id = id;
        setLicensePlate("");
        setChassis("");
        setManufactureYear(1900);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

}