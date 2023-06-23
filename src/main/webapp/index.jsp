<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="CSS/cadastro.css">
</head>
<body>
<form class="centralized" action="LoginServlet" method="post">
    <label for="login">Login</label>
    <input type="text" name="input_login" id="login"required>
    <label for="senha">Senha</label>
    <input type="text" name="input_senha" id="senha"required>
    <input class="button-green" type="submit" value="Entrar">
    <%if (request.getParameter("erro") != null) { %>
        <p style='color:red'><%= request.getParameter("erro") %></p>
    <%}%>
</form>


</body>
</html>