package com.controledebiblioteca.controledebiblioteca.utils;

public class LocalHost {

    public static final String link = "http://localhost:8080/controle_de_biblioteca_war_exploded/";
    public static final String redirectLivros = LocalHost.link + "RedirectServlet?id=livros";
    public static final String redirectAutores = LocalHost.link + "RedirectServlet?id=autores";
    public static final String redirectUsuarios = LocalHost.link + "RedirectServlet?id=usuarios";
}
