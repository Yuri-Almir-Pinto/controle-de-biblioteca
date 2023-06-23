<%--
  Created by IntelliJ IDEA.
  User: zphyr
  Date: 19/06/2023
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.models.entities.Livros" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.models.enums.LivroStatus" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.utils.LocalHost" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Livros</title>
    <link rel="stylesheet" href="CSS/mostrarConteudo.css">
    <script src="javascript/mostrarLivros.js"></script>
</head>
<body>

    <form action="EditarStatusLivrosServlet" method="post" id="form">
        <% if (request.getParameter("erro") != null) { %>
        <p style="color: red;"><%=request.getParameter("erro")%></p>
        <% } %>
        <button class="button-deactivated" type="submit" id="submit_button" disabled>Finalizar mudanças</button>
        <a class="button-green right-button" type="button"
           href="<%=LocalHost.link + "RedirectServlet"%>">Voltar</a>
        <button class="button-green" type="button"
                onclick="window.location.href='<%=LocalHost.link + "CadastrarLivroServlet"%>'">Cadastrar Livro</button>
        <div class="table-div">
            <table>
                <tr>
                    <td>ID</td>
                    <td>Nome</td>
                    <td>Autor</td>
                    <td>Data de criação</td>
                    <td>Status</td>
                    <td>Ação</td>
                </tr>
                <%
                    ArrayList<Livros> livros = (ArrayList<Livros>) request.getAttribute("livros");

                    if (livros != null && !livros.isEmpty()) {
                        for (Livros livro : livros) {
                %>
                <tr>
                    <td><%= livro.getId() %></td>
                    <td><%= livro.getNome() %></td>
                    <td><%= livro.getAutor().getNome() %></td>
                    <td><%= livro.getData() %></td>
                    <td>
                        <select onchange="estadoSelect(this, <%=livro.getId()%>)">
                            <option value="DISPONIVEL" <% if (livro.getStatus() == LivroStatus.DISPONIVEL) { %>selected<% } %>>DISPONIVEL</option>
                            <option value="EMPRESTADO" <% if (livro.getStatus() == LivroStatus.EMPRESTADO) { %>selected<% } %>>EMPRESTADO</option>
                            <option value="INDISPONIVEL" <% if (livro.getStatus() == LivroStatus.INDISPONIVEL) { %>selected<% } %>>INDISPONIVEL</option>
                        </select>
                    </td>
                    <td>
                        <button  type="button" onclick="deletar(<%=livro.getId()%>)"
                                <% if(livro.getStatus() != LivroStatus.INDISPONIVEL) { %>
                                 class="button-deactivated" disabled
                                <%} else {%>
                                 class="button-red"
                                <%}%>
                        >Deletar
                        </button>
                        <button class="button-green" type="button" onclick="window.location.href='<%=LocalHost.link + "EditarLivroServlet?id=" + livro.getId()%>'"
                        >Editar
                        </button>
                    </td>
                </tr>
                <%}
                }%>
            </table>
        </div>
    </form>
</body>
</html>
