package com.controledebiblioteca.controledebiblioteca.controllers;

import com.controledebiblioteca.controledebiblioteca.models.entities.Autores;
import com.controledebiblioteca.controledebiblioteca.models.repository.AutoresRepository;
import com.controledebiblioteca.controledebiblioteca.utils.LocalHost;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CadastrarAutorServlet", value = "/CadastrarAutorServlet")
public class CadastrarAutorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("MostrarCadastroAutorServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AutoresRepository repository = new AutoresRepository();
        Autores autor = new Autores();

        autor.setNome(request.getParameter("autor_nome"));

        repository.createAutor(autor);

        response.sendRedirect(LocalHost.redirectAutores);
    }
}
