package com.example.Clinica.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter //Cria os getters
@Setter //Cria os setters
@AllArgsConstructor //Cria os construtores
public class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String nivelacesso;


//Construtor sem ID
    public Usuario(String nome, String email, String senha, String nivelacesso) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.nivelacesso = nivelacesso;
    }
}
