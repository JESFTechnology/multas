package com.ifsuldeminas.edu.br.multas.model.entities;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "policiais")
public class Police {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "O nome do policial não pode ser vazio.")
    private String name;

    @NotBlank(message = "A patente do policial não pode ser vazia.")
    private String rank;

    @NotBlank(message = "O CPF do policial não pode ser vazio.")
    @Length(min = 11, max = 11, message = "O CPF deve conter 11 caracteres.")
    private String cpf;

    @NotBlank(message = "O email do policial não pode ser vazio.")
    @Email(message = "Endereço de email inválido.")
    private String email;

    @NotBlank(message = "A senha não pode ser vazia.")
    private String password;

    public Police() {}

    Police(Integer id){
        this.id = id;
        setName("");
        setRank("");
        setCpf("");
        setEmail("");
        setPassword("");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}