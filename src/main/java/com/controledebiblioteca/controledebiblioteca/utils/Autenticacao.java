package com.controledebiblioteca.controledebiblioteca.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

public class Autenticacao {

    public static final String erro = "Erro desconhecido.";
    public static final String invalido = "Erro: Senha ou login invalidos.";
    public static final String insira = "Insira seu login e senha.";

    public static boolean isLoggedIn (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Boolean isLoggedIn = (Boolean) session.getAttribute("is_logged_in");
            if (isLoggedIn != null && isLoggedIn) {
                return true;
            }
        }
        return false;
    }

    public static void erroAutenticacao (HttpServletResponse response, String mensagem) throws IOException {
        response.sendRedirect(LocalHost.link + "?erro=" + mensagem);
    }
}
