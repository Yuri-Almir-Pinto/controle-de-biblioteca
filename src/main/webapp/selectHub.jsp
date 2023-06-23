<%@ page import="com.controledebiblioteca.controledebiblioteca.models.entities.Usuarios" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.utils.Autenticacao" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.utils.LocalHost" %><%--
  Created by IntelliJ IDEA.
  User: zphyr
  Date: 21/06/2023
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Hub</title>
    <link rel="stylesheet" href="CSS/selectHub.css">
    <script src="javascript/selectHub.js"></script>
</head>
<body>

<form method="post" action="RedirectServlet" id="form">
    <div class="window">
        <div class="centralize">
            <h1>Acesso</h1>
        </div>
        <div class="centralize">
            <a class="button-blue" type="button" id="livros" href="<%=LocalHost.redirectLivros%>">Livros</a>
            <a class="button-blue" type="button" id="autores" href="<%=LocalHost.redirectAutores%>">Autores</a>
            <%if (Autenticacao.isAdmin(request)) {%>
            <a class="button-blue" type="button" id="usuarios" href="<%=LocalHost.redirectUsuarios%>">Usuários</a>
            <%}%>
        </div>
        <div class="centralize">
            <a class=button-red id="logout" href="<%=LocalHost.link + "LogoutServlet"%>">Logout</a>
            <%if (Autenticacao.isAdmin(request)) {%>
            <a class="button-red"
               type="button"
               id="usuarios"
               href="<%=LocalHost.link + "ReiniciarBancoServlet"%>"
               style="width: 180px">Reiniciar Banco</a>
            <%}%>
        </div>
    </div>
</form>


</body>
</html>
