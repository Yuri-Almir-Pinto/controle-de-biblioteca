<%--
  Created by IntelliJ IDEA.
  User: zphyr
  Date: 19/06/2023
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.models.entities.Livros" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.models.enums.LivroStatus" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="CSS/padrao.css">
    <script src="javascript/mostrarLivros.js"></script>
</head>
<body>
    <form action="EditarStatusLivrosServlet" method="post">
        <table>
            <tr>
                <td>ID</td>
                <td>Nome</td>
                <td>Autor</td>
                <td>Data de criação</td>
                <td>Status</td>
            </tr>
            <%
                List<Livros> livros = (List<Livros>) request.getAttribute("livros");

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
            </tr>
            <%}
            }%>
        </table>

        <button type="submit" id="submit_button" disabled>Finalizar mudanças</button>
    </form>
</body>
</html>
