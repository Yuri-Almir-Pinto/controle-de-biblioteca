package com.controledebiblioteca.controledebiblioteca.filters;

import com.controledebiblioteca.controledebiblioteca.controllers.LoginServlet;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.controledebiblioteca.controledebiblioteca.utils.Autenticacao;

import java.io.IOException;

@WebFilter(filterName = "AutenticationFilter", value = {"/RedirectServlet", "/EditarStatusLivrosServlet",
"/MostrarLivrosServlet", "/DeletarLivroServlet", "/EditarLivroServlet", "/CadastrarLivroServlet",
"/EditarNomeAutorServlet", "/CadastrarAutorServlet", "/DeletarAutorServlet",
"/MostrarAutoresServlet", "/MostrarCadastroAutorServlet", "/MostrarCadastroLivroServlet",
"/MostrarSelectHubServlet", "/MostrarUsuariosServlet"})
public class AutenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (Autenticacao.isLoggedIn((HttpServletRequest) request)) {
            chain.doFilter(request, response);
        } else {
            Autenticacao.erroAutenticacao((HttpServletRequest) request, (HttpServletResponse) response, "Insira seu login e senha.");
        }
    }
}
