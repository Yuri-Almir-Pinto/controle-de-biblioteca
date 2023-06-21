package com.controledebiblioteca.controledebiblioteca.models.entities;

import com.controledebiblioteca.controledebiblioteca.models.enums.LivroStatus;

import java.time.LocalDate;

public class Livros {

    private Integer id;
    private String nome;
    private LocalDate data;
    private Autores autor;

    public LivroStatus getStatus() {
        return status;
    }

    public void setStatus(LivroStatus status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = LivroStatus.valueOf(status);
    }

    private LivroStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }
}
