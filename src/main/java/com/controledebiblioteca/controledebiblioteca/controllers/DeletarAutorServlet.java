package com.controledebiblioteca.controledebiblioteca.controllers;

import com.controledebiblioteca.controledebiblioteca.models.repository.AutoresRepository;
import com.controledebiblioteca.controledebiblioteca.models.repository.LivrosRepository;
import com.controledebiblioteca.controledebiblioteca.utils.LocalHost;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeletarAutorServlet", value = "/DeletarAutorServlet")
public class DeletarAutorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AutoresRepository repository = new AutoresRepository();

            Integer id = Integer.parseInt(request.getParameter("autor_id"));
            if (repository.deleteAutor(id)) {
                response.sendRedirect(LocalHost.redirectAutores);
            }
            else {
                response.sendRedirect(LocalHost.redirectAutores + "&erro=Erro: Autor nao encontrado.");
            }

        }
        catch (NullPointerException | NumberFormatException e1) {
            response.sendRedirect(LocalHost.redirectAutores + "&erro=Erro: Autor nao encontrado.");
        }
    }
}
