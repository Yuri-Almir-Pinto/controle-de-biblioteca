package com.controledebiblioteca.controledebiblioteca.controllers;

import com.controledebiblioteca.controledebiblioteca.models.repository.LivrosRepository;
import com.controledebiblioteca.controledebiblioteca.models.repository.UsuariosRepository;
import com.controledebiblioteca.controledebiblioteca.utils.LocalHost;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeletarUsuarioServlet", value = "/DeletarUsuarioServlet")
public class DeletarUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UsuariosRepository repository = new UsuariosRepository();

            int id = Integer.parseInt(request.getParameter("usuario_id"));
            if (repository.deleteUsuario(id)) {
                response.sendRedirect(LocalHost.redirectUsuarios);
            }
            else {
                response.sendRedirect(LocalHost.redirectUsuarios + "&erro=Erro: Usuario nao encontrado.");
            }

        }
        catch (NullPointerException | NumberFormatException e1) {
            response.sendRedirect(LocalHost.redirectUsuarios + "&erro=Erro: Usuario nao encontrado.");
        }
    }
}
