<%@ page import="com.controledebiblioteca.controledebiblioteca.models.entities.Livros" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.models.entities.Autores" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.models.enums.LivroStatus" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.utils.LocalHost" %><%--
  Created by IntelliJ IDEA.
  User: zphyr
  Date: 21/06/2023
  Time: 02:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Cadasro Autor</title>
  <link rel="stylesheet" href="CSS/cadastro.css">
</head>
<body>
  <form action="CadastrarAutorServlet" method="post">
    <%
      Autores autor = (Autores) request.getAttribute("autor");
    %>
    <h1>Cadastrar Autor</h1>
    <label for="nome">Nome:</label>
    <input required type="text" id="nome" name="autor_nome">

    <button type="submit" class="button-green">Salvar</button>
    <a type="button" class="button-red"
       href="<%=LocalHost.redirectAutores%>">Cancelar</a>
    <%if (request.getAttribute("erro") != null) { %>
    <p style="color: red"><%=request.getAttribute("erro")%></p>
    <% } else if (request.getParameter("erro") != null) {%>
    <p style="color: red"><%=request.getParameter("erro")%></p>
    <% } %>
  </form>
</body>
</html>
