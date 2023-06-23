<%@ page import="com.controledebiblioteca.controledebiblioteca.utils.LocalHost" %><%--
  Created by IntelliJ IDEA.
  User: zphyr
  Date: 22/06/2023
  Time: 03:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro Usuario</title>
    <link rel="stylesheet" href="CSS/cadastro.css">
</head>
<body>
    <form action="CadastrarUsuarioServlet" method="post">
        <h1>Cadastrar Usuario</h1>
        <label for="login">Login:</label>
        <input required type="text" id="login" name="usuario_nome">

        <label for="senha">Senha:</label>
        <input required type="text" id="senha" name="usuario_senha">

        <button type="submit" class="button-green">Salvar</button>
        <a type="button" class="button-red"
           href="<%=LocalHost.redirectUsuarios%>">Cancelar</a>
        <%if (request.getAttribute("erro") != null) { %>
        <p style="color: red"><%=request.getAttribute("erro")%></p>
        <% } else if (request.getParameter("erro") != null) {%>
        <p style="color: red"><%=request.getParameter("erro")%></p>
        <% } %>
    </form>
</body>
</html>
