package com.controledebiblioteca.controledebiblioteca.controllers;

import com.controledebiblioteca.controledebiblioteca.models.repository.LivrosRepository;
import com.controledebiblioteca.controledebiblioteca.utils.Autenticacao;
import com.controledebiblioteca.controledebiblioteca.utils.LocalHost;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeletarLivroServlet", value = "/DeletarLivroServlet")
public class DeletarLivroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LivrosRepository repository = new LivrosRepository();

            int id = Integer.parseInt(request.getParameter("livro_id"));
            if (repository.deleteLivro(id)) {
                response.sendRedirect(LocalHost.redirectLivros);
            }
            else {
                response.sendRedirect(LocalHost.redirectLivros + "&erro=Erro: Livro nao esta indisponivel");
            }

        }
        catch (NullPointerException | NumberFormatException e1) {
            response.sendRedirect(LocalHost.redirectLivros + "&erro=Erro: Livro nao encontrado.");
        }

    }
}
