package com.controledebiblioteca.controledebiblioteca.models.entities;

public class Autores {
    private Integer id;
    private String nome;

    public Autores() {}

    public Autores(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    public Integer getId() throws NullPointerException {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() throws NullPointerException {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
