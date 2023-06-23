package com.controledebiblioteca.controledebiblioteca.controllers;

import com.controledebiblioteca.controledebiblioteca.models.entities.Autores;
import com.controledebiblioteca.controledebiblioteca.models.repository.AutoresRepository;
import com.controledebiblioteca.controledebiblioteca.utils.LocalHost;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet(name = "EditarNomeAutoresServlet", value = "/EditarNomeAutoresServlet")
public class EditarNomeAutoresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> nomeParametros = request.getParameterNames();
        ArrayList<Autores> autoresResponse = new ArrayList<>();
        AutoresRepository repository = new AutoresRepository();

        while (nomeParametros.hasMoreElements()) {
            String parametro = nomeParametros.nextElement();
            String[] split = parametro.split("_");
            String valor = request.getParameter(parametro);

            autoresResponse.add(new Autores(Integer.parseInt(split[1]), valor));
        }
        repository.updateAutores(autoresResponse);
        response.sendRedirect(LocalHost.redirectAutores);
    }
}
