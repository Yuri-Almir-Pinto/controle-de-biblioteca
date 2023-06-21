package com.controledebiblioteca.controledebiblioteca.models.entities;

import com.controledebiblioteca.controledebiblioteca.models.enums.LivroStatus;

public class LivroEnumResponse {
    public Integer id;
    public LivroStatus status;

    public LivroEnumResponse(Integer id, String status) throws IllegalArgumentException {
        this.id = id;
        this.status = LivroStatus.valueOf(status);
    }
}
