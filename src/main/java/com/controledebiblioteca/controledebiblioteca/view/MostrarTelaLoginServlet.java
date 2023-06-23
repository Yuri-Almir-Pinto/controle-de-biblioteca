package com.controledebiblioteca.controledebiblioteca.view;

import com.controledebiblioteca.controledebiblioteca.utils.LocalHost;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MostrarTelaLoginServlet", value = "/MostrarTelaLoginServlet")
public class MostrarTelaLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String erro = (String) request.getAttribute("erro");
        if (erro != null) {
            response.sendRedirect(LocalHost.link + "?erro=" + erro);
        }
        else {
            response.sendRedirect(LocalHost.link);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
