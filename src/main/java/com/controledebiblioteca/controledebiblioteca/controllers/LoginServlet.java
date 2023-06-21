package com.controledebiblioteca.controledebiblioteca.controllers;

import java.io.*;

import com.controledebiblioteca.controledebiblioteca.models.entities.Usuario;
import com.controledebiblioteca.controledebiblioteca.utils.LocalHost;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.controledebiblioteca.controledebiblioteca.utils.Autenticacao;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        try {
            if (Autenticacao.isLoggedIn(request)) {
                response.sendRedirect(LocalHost.link + "RedirectServlet");
            }
            else {
                String login = request.getParameter("input_login");
                String senha = request.getParameter("input_senha");

                Usuario usuario = new Usuario(login, senha);

                if (Usuario.checkIfAdmin(usuario)) {

                    setSession(request, usuario);
                    response.sendRedirect(LocalHost.link + "RedirectServlet");
                }

                else {
                    Autenticacao.erroAutenticacao(response, Autenticacao.invalido);
                }
            }
        }
        catch(Exception e) {
            Autenticacao.erroAutenticacao(response, Autenticacao.erro);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Autenticacao.isLoggedIn(request)) {
            Autenticacao.erroAutenticacao(response, Autenticacao.insira);
        }
    }

    public void destroy() {
    }

    protected void setSession (HttpServletRequest request, Usuario usuario) {
        HttpSession session = request.getSession();
        session.setAttribute("is_logged_in", true);
        session.setAttribute("usuario", usuario);
        session.setMaxInactiveInterval(60*5);
    }

}