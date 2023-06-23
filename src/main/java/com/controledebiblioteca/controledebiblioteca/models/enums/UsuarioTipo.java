package com.controledebiblioteca.controledebiblioteca.models.enums;

import com.controledebiblioteca.controledebiblioteca.models.entities.Usuarios;

public enum UsuarioTipo {

    ADMINISTRADOR("ADMINISTRADOR"),
    CLIENTE("CLIENTE");

    private final String tipo;

    UsuarioTipo (String tipo) {
    this.tipo = tipo;
    }

    public String getTipo () {
        return this.tipo;
    }
}
