package com.controledebiblioteca.controledebiblioteca.view;

import com.controledebiblioteca.controledebiblioteca.utils.Reference;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MostrarUsuariosServlet", value = "/MostrarUsuariosServlet")
public class MostrarUsuariosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Reference.mostrarUsuarios).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
