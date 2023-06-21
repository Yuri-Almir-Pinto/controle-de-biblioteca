package com.controledebiblioteca.controledebiblioteca.models.enums;

public enum LivroStatus {
    DISPONIVEL("DISPONIVEL"),
    EMPRESTADO("EMPRESTADO"),
    INDISPONIVEL("INDISPONIVEL");

    private final String status;

    LivroStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
