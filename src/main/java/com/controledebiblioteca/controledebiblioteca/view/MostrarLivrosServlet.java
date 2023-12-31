package com.controledebiblioteca.controledebiblioteca.view;

import com.controledebiblioteca.controledebiblioteca.utils.Autenticacao;
import com.controledebiblioteca.controledebiblioteca.utils.Reference;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MostrarLivrosServlet", value = "/MostrarLivrosServlet")
public class MostrarLivrosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Reference.mostrarLivros).forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

}
