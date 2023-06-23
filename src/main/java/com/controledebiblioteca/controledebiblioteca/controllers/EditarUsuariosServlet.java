package com.controledebiblioteca.controledebiblioteca.controllers;

import com.controledebiblioteca.controledebiblioteca.exception.UsuarioEditOperationException;
import com.controledebiblioteca.controledebiblioteca.models.entities.Usuarios;
import com.controledebiblioteca.controledebiblioteca.models.repository.UsuariosRepository;
import com.controledebiblioteca.controledebiblioteca.utils.LocalHost;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

@WebServlet(name = "EditarUsuariosServlet", value = "/EditarUsuariosServlet")
public class EditarUsuariosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> nomeParametros = request.getParameterNames();
        HashMap<Integer, Usuarios> usuariosMap = new HashMap<>();
        ArrayList<Usuarios> usuariosList = new ArrayList<>();
        UsuariosRepository repository = new UsuariosRepository();

        while (nomeParametros.hasMoreElements()) {
            String parametro = nomeParametros.nextElement();

            String[] split = parametro.split("_");
            String campo = split[0];
            Integer id = Integer.parseInt(split[1]);
            String valor = request.getParameter(parametro);

            try {
                if (!usuariosMap.containsKey(id)) {
                    usuariosMap.put(id, new Usuarios());
                }
                if (campo.equals("login")) {
                    usuariosMap.get(id).setLogin(valor);
                }
                else if (campo.equals("senha")) {
                    usuariosMap.get(id).setSenha(valor);
                }
                else {
                    throw new UsuarioEditOperationException("&erro=Erro: Operacao invalida.");
                }

            }
            catch (UsuarioEditOperationException e1) {
                response.sendRedirect(LocalHost.redirectUsuarios + e1.getMessage());
                return;
            }
        }

        usuariosMap.forEach((id, usuario) -> {
            usuario.setId(id);
            usuariosList.add(usuario);
        });

        if (repository.updateUsuarios(usuariosList)) {
            response.sendRedirect(LocalHost.redirectUsuarios + "&erro=Erro: Um ou mais usuarios estavam com logins duplicados. Nenhuma mudanca foram feitas nos mesmos.");
        }
        else {
            response.sendRedirect(LocalHost.redirectUsuarios);
        }

    }


}
