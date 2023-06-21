package com.controledebiblioteca.controledebiblioteca.models.entities;

import com.controledebiblioteca.controledebiblioteca.models.enums.UsuarioTipo;

public class Usuario {

    private String id;
    private String login;
    private String senha;
    private UsuarioTipo tipo;

    public Usuario(String login, String senha) {
        setLogin(login);
        setSenha(senha);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public static boolean checkIfAdmin(Usuario usuario) {
        if (usuario.getLogin().equals("admin") && usuario.getSenha().equals("admin")) {
            return true;
        }
        return false;
    }
}
