<%@ page import="com.controledebiblioteca.controledebiblioteca.utils.LocalHost" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.models.entities.Autores" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: zphyr
  Date: 21/06/2023
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Autores</title>
    <link rel="stylesheet" href="CSS/mostrarConteudo.css">
    <script src="javascript/mostrarAutores.js"></script>
</head>
<body>

    <form action="EditarNomeAutoresServlet" method="post" id="form">
        <% if (request.getParameter("erro") != null) { %>
        <p style="color: red;"><%=request.getParameter("erro")%></p>
        <% } %>
        <button class="button-deactivated" type="submit" id="submit_button" disabled>Finalizar mudanças</button>
        <a class="button-green right-button" type="button"
           href="<%=LocalHost.link + "RedirectServlet"%>">Voltar</a>
        <button class="button-green" type="button"
                onclick="window.location.href='<%=LocalHost.link + "CadastrarAutorServlet"%>'">Cadastrar Autor</button>
        <div class="table-div">
            <table>
                <tr>
                    <td style="width: auto">ID</td>
                    <td style="width: 100%">Nome</td>
                    <td style="width: auto">Ação</td>
                </tr>
                <%
                    ArrayList<Autores> autores = (ArrayList<Autores>) request.getAttribute("autores");

                    if (autores != null && !autores.isEmpty()) {
                        for (Autores autor : autores) {
                %>
                <tr>
                    <td><%= autor.getId() %></td>
                    <td>
                        <input value="<%= autor.getNome() %>"
                               onkeyup="nomeMudado(this, <%=autor.getId()%>)"
                               style="width: 100%">
                    </td>
                    <td>
                        <button  type="button" onclick="deletar(<%=autor.getId()%>)"
                                 class="button-red">Deletar
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
