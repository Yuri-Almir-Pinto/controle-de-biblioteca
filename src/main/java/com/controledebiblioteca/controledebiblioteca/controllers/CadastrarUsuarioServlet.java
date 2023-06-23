package com.controledebiblioteca.controledebiblioteca.controllers;

import com.controledebiblioteca.controledebiblioteca.models.entities.Autores;
import com.controledebiblioteca.controledebiblioteca.models.entities.Usuarios;
import com.controledebiblioteca.controledebiblioteca.models.repository.AutoresRepository;
import com.controledebiblioteca.controledebiblioteca.models.repository.UsuariosRepository;
import com.controledebiblioteca.controledebiblioteca.utils.LocalHost;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CadastrarUsuarioServlet", value = "/CadastrarUsuarioServlet")
public class CadastrarUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("MostrarCadastroUsuarioServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuariosRepository repository = new UsuariosRepository();
        Usuarios usuario = new Usuarios();

        usuario.setLogin(request.getParameter("usuario_nome"));
        usuario.setSenha(request.getParameter("usuario_senha"));

        if (repository.createUsuario(usuario)) {
            response.sendRedirect(LocalHost.redirectUsuarios);
        }
        else {
            response.sendRedirect(LocalHost.redirectUsuarios + "&erro=Erro: Login ja existente.");
        }


    }
}
