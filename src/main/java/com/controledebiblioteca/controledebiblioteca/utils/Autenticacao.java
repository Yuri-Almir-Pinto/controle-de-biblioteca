package com.controledebiblioteca.controledebiblioteca.utils;

import com.controledebiblioteca.controledebiblioteca.controllers.LogoutServlet;
import com.controledebiblioteca.controledebiblioteca.models.entities.Usuarios;
import com.controledebiblioteca.controledebiblioteca.models.enums.UsuarioTipo;
import jakarta.servlet.ServletException;
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

    public static boolean isAdmin (HttpServletRequest request) {
        if (isLoggedIn(request)) {
            HttpSession session = request.getSession(false);
            Usuarios usuario = (Usuarios) session.getAttribute("usuario");
            if (isValid(usuario) && usuario.getTipo() == UsuarioTipo.ADMINISTRADOR) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValid (Usuarios usuario) {
        if (usuario != null) {
            if (usuario.getId() != null) {
                return true;
            }
        }
        return false;
    }

    public static void erroAutenticacao (HttpServletRequest request, HttpServletResponse response, String mensagem) throws IOException, ServletException {
        request.setAttribute("erro", mensagem);
        request.getRequestDispatcher("LogoutServlet").forward(request, response);
    }
}
