package com.controledebiblioteca.controledebiblioteca.controllers;

import com.controledebiblioteca.controledebiblioteca.models.entities.Autores;
import com.controledebiblioteca.controledebiblioteca.models.entities.Livros;
import com.controledebiblioteca.controledebiblioteca.models.repository.AutoresRepository;
import com.controledebiblioteca.controledebiblioteca.models.repository.LivrosRepository;
import com.controledebiblioteca.controledebiblioteca.utils.LocalHost;
import com.controledebiblioteca.controledebiblioteca.utils.Reference;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

@WebServlet(name = "CadastrarLivroServlet", value = "/CadastrarLivroServlet")
public class CadastrarLivroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AutoresRepository repository = new AutoresRepository();
        ArrayList<Autores> autores = repository.readAllAutores();

        request.setAttribute("autores", autores);
        request.setAttribute("action", "CadastrarLivroServlet");
        request.getRequestDispatcher("MostrarCadastroLivroServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LivrosRepository livrosRepository = new LivrosRepository();
        AutoresRepository autoresRepository = new AutoresRepository();

        Livros livro = new Livros();

        try {
            // Setando nome
            livro.setNome(request.getParameter("livro_nome"));

            // Setando e tratando data
            livro.setData(LocalDate.parse(request.getParameter("livro_data")));

            // Setando e tratando autor
            int id = Integer.parseInt(request.getParameter("livro_autor"));
            livro.setAutor(autoresRepository.readAutor(id));
            if (livro.getAutor().getId() == null) {
                throw new NullPointerException();
            }

            livro.setStatus(request.getParameter("livro_status"));
        }
        catch (DateTimeParseException e1) {
            response.sendRedirect(LocalHost.link + "CadastrarLivroServlet?erro=Data inserida invalida.&id=" + livro.getId());
            return;
        }
        catch (NumberFormatException e2) {
            response.sendRedirect(LocalHost.redirectLivros + "&erro=Erro: Id invalido");
            return;
        }
        catch (IllegalArgumentException e3) {
            response.sendRedirect(LocalHost.link + "CadastrarLivroServlet?erro=Status invalido.&id=" + livro.getId());
            return;
        }
        catch (NullPointerException e4) {
            response.sendRedirect(LocalHost.link + "CadastrarLivroServlet?erro=Autor invalido.&id=" + livro.getId());
            return;
        }

        livrosRepository.createLivro(livro);

        response.sendRedirect(LocalHost.redirectLivros);
    }
}
