<%@ page import="com.controledebiblioteca.controledebiblioteca.utils.LocalHost" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.models.entities.Usuarios" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.models.enums.UsuarioTipo" %>
<%@ page import="com.controledebiblioteca.controledebiblioteca.controllers.EditarUsuariosServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: zphyr
  Date: 22/06/2023
  Time: 02:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Usuarios</title>
    <link rel="stylesheet" href="CSS/mostrarConteudo.css">
    <script src="javascript/mostrarUsuarios.js"></script>
</head>
<body>

    <form action="EditarUsuariosServlet" method="post" id="form">
        <% if (request.getParameter("erro") != null) { %>
        <p style="color: red;"><%=request.getParameter("erro")%></p>
        <% } %>
        <button class="button-deactivated" type="submit" id="submit_button" disabled>Finalizar mudanças</button>
        <button class="button-green right-button" type="button"
                onclick="window.location.href='<%=LocalHost.link + "RedirectServlet"%>'">Voltar</button>
        <button class="button-green" type="button"
                onclick="window.location.href='<%=LocalHost.link + "CadastrarUsuarioServlet"%>'">Cadastrar Usuario</button>
        <div class="table-div">
            <table>
                <tr>
                    <td>ID</td>
                    <td>Login</td>
                    <td>Senha</td>
                    <td>Tipo</td>
                    <td>Ação</td>
                </tr>
                <%
                    ArrayList<Usuarios> usuarios = (ArrayList<Usuarios>) request.getAttribute("usuarios");

                    if (usuarios != null && !usuarios.isEmpty()) {
                        for (Usuarios usuario : usuarios) {
                %>
                <tr<%if (usuario.getTipo() == UsuarioTipo.ADMINISTRADOR) {%> class="red-highlight" <%}%>>
                    <td><%= usuario.getId() %></td>
                    <td>
                        <input value="<%= usuario.getLogin() %>"
                               onkeyup="loginMudado(this, <%=usuario.getId()%>)">
                    </td>
                    <td>
                        <input value="<%= usuario.getSenha() %>"
                               onkeyup="senhaMudado(this, <%=usuario.getId()%>)">
                    </td>
                    <td><%= usuario.getTipo() %></td>
                    <td>
                        <button  type="button" onclick="deletar(<%=usuario.getId()%>)"
                                 <% if (usuario.getTipo() == UsuarioTipo.ADMINISTRADOR) { %>
                                 class="button-deactivated" disabled
                                <%} else {%>
                                 class="button-red"
                                <%}%>
                        >Deletar
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
