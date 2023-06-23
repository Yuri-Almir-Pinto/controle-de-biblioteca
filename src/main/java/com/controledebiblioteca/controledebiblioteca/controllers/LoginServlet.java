package com.controledebiblioteca.controledebiblioteca.controllers;

import java.io.*;

import com.controledebiblioteca.controledebiblioteca.models.entities.Usuarios;
import com.controledebiblioteca.controledebiblioteca.models.repository.UsuariosRepository;
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
        // Ao tentar dar login no index, cai aqui.
        try {
            if (Autenticacao.isLoggedIn(request)) {
                response.sendRedirect(LocalHost.link + "RedirectServlet");
            } // Manda para a próxima página se já estiver numa sessão, independente dos dados.
            else {
                UsuariosRepository repository = new UsuariosRepository();
                String login = request.getParameter("input_login");
                String senha = request.getParameter("input_senha");

                Usuarios usuario = repository.readUsuarioAutenticacao(new Usuarios(login, senha));
                // Se não estiver, verifica ‘login’ e senha.
                // Se for o admin., cria uma sessão e redireciona para a próxima servlet.
                if (Autenticacao.isValid(usuario)) {
                    setSession(request, usuario);
                    response.sendRedirect(LocalHost.link + "RedirectServlet");
                }

                else {
                    Autenticacao.erroAutenticacao(request, response, Autenticacao.invalido);
                }
            }
        }
        catch(Exception e) {
            Autenticacao.erroAutenticacao(request, response, Autenticacao.erro);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Autenticacao.isLoggedIn(request)) {
            Autenticacao.erroAutenticacao(request, response, Autenticacao.insira);
        }
    }

    public void destroy() {
    }

    protected void setSession (HttpServletRequest request, Usuarios usuario) {
        HttpSession session = request.getSession();
        session.setAttribute("is_logged_in", true);
        session.setAttribute("usuario", usuario);
        session.setMaxInactiveInterval(60*5);
    }

}