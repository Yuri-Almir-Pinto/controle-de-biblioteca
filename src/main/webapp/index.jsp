<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="LoginServlet" method="post">
    <p>Usuário: <input type="text" name="input_login" required></p>
    <p>Senha: <input type="text" name="input_senha" required></p>
    <input type="submit" value="Entrar">
    <%if (request.getParameter("erro") != null) { %>
        <p style='color:red'><%= request.getParameter("erro") %></p>
    <%}%>
</form>


</body>
</html>