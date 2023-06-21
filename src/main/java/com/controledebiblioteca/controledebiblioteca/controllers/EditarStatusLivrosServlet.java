package com.controledebiblioteca.controledebiblioteca.controllers;

import com.controledebiblioteca.controledebiblioteca.models.entities.LivroEnumResponse;
import com.controledebiblioteca.controledebiblioteca.utils.Autenticacao;
import com.controledebiblioteca.controledebiblioteca.models.repository.LivrosRepository;
import com.controledebiblioteca.controledebiblioteca.utils.LocalHost;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet(name = "EditarStatusLivrosServlet", value = "/EditarStatusLivrosServlet")
public class EditarStatusLivrosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> nomeParametros = request.getParameterNames();
        ArrayList<LivroEnumResponse> livrosEnumResponse = new ArrayList<>();
        LivrosRepository repository = new LivrosRepository();

        while (nomeParametros.hasMoreElements()) {
            String parametro = nomeParametros.nextElement();
            String[] split = parametro.split("_");
            String valor = request.getParameter(parametro);

            livrosEnumResponse.add(new LivroEnumResponse(Integer.parseInt(split[1]), valor));
        }
        repository.updateLivrosStatus(livrosEnumResponse);
        response.sendRedirect(LocalHost.link + "RedirectServlet");
    }

}
