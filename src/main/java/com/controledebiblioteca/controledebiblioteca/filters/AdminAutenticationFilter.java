package com.controledebiblioteca.controledebiblioteca.filters;

import com.controledebiblioteca.controledebiblioteca.utils.Autenticacao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "AdminAutenticationFilter", value = {"/MostrarUsuariosServlet",
"/CadastrarUsuarioServlet", "/MostrarCadastroUsuarioServlet", "/DeletarUsuarioServlet",
"/EditarUsuariosServlet", "/ReiniciarBancoServlet"})
public class AdminAutenticationFilter implements Filter {
    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (Autenticacao.isLoggedIn((HttpServletRequest) request) && Autenticacao.isAdmin((HttpServletRequest) request)) {
            chain.doFilter(request, response);
        } else {
            Autenticacao.erroAutenticacao((HttpServletRequest) request, (HttpServletResponse) response, "Voce nao possui autorizacao para acessar esta pagina.");
        }
    }
}
