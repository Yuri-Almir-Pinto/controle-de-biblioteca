package com.controledebiblioteca.controledebiblioteca.controllers;

import com.controledebiblioteca.controledebiblioteca.models.entities.Autores;
import com.controledebiblioteca.controledebiblioteca.models.entities.Livros;
import com.controledebiblioteca.controledebiblioteca.models.entities.Usuarios;
import com.controledebiblioteca.controledebiblioteca.models.repository.AutoresRepository;
import com.controledebiblioteca.controledebiblioteca.models.repository.LivrosRepository;
import com.controledebiblioteca.controledebiblioteca.models.repository.UsuariosRepository;
import com.controledebiblioteca.controledebiblioteca.utils.Autenticacao;
import com.controledebiblioteca.controledebiblioteca.utils.Reference;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RedirectServlet", value = "/RedirectServlet")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            request.getRequestDispatcher("MostrarSelectHubServlet").forward(request, response);
        }
        else if (id.equals("livros") || id.equals("autores") || id.equals("usuarios")) {
            doPost(request, response);
        }
        else {
            request.getRequestDispatcher("MostrarSelectHubServlet").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id.equals("livros")) {
            LivrosRepository repository = new LivrosRepository();
            ArrayList<Livros> livros = repository.readAllLivros();

            request.setAttribute("livros", livros);
            request.getRequestDispatcher("MostrarLivrosServlet").forward(request, response);
        }
        else if (id.equals("autores")) {
            AutoresRepository repository = new AutoresRepository();
            ArrayList<Autores> autores = repository.readAllAutores();

            request.setAttribute("autores", autores);
            request.getRequestDispatcher("MostrarAutoresServlet").forward(request, response);
        }
        else if (id.equals("usuarios")) {
            UsuariosRepository repository = new UsuariosRepository();
            ArrayList<Usuarios> usuarios = repository.readAllUsuarios();

            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("MostrarUsuariosServlet").forward(request, response);
        }
        else {
            doGet(request, response);
        }
    }
}
