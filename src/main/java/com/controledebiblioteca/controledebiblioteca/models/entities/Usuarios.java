package com.controledebiblioteca.controledebiblioteca.models.entities;

import com.controledebiblioteca.controledebiblioteca.models.enums.UsuarioTipo;

public class Usuarios {

    private Integer id;
    private String login;
    private String senha;
    private UsuarioTipo tipo;

    public Usuarios(String login, String senha) {
        setLogin(login);
        setSenha(senha);
    }

    public Usuarios() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;

    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;

    }

    public UsuarioTipo getTipo() {
        return tipo;
    }

    public void setTipo(UsuarioTipo tipo) {
        this.tipo = tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = UsuarioTipo.valueOf(tipo);
    }
}
