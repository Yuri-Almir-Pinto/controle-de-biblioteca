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
    <title>Title</title>
    <link rel="stylesheet" href="CSS/cadastro.css">
</head>
<body>
<form action="<%= request.getAttribute("action") %>" method="post">
  <%
    Boolean temLivro = null;
    Livros livro = null;
    ArrayList<Autores> autores = (ArrayList<Autores>) request.getAttribute("autores");
  if (request.getAttribute("livro") != null) {
    temLivro = true;
    livro = (Livros) request.getAttribute("livro");
  } else {
    temLivro = false;
  }
  %>
  <%if (temLivro) {%>
    <h1>Editar Livro</h1>
  <%} else {%>
    <h1>Cadastrar Livro</h1>
  <% } %>
  <input type="hidden" name="livro_id" value="<% if(temLivro) {%><%= livro.getId() %><%}%>">

  <label for="nome">Nome:</label>
  <input required type="text" id="nome" name="livro_nome" value="<% if(temLivro) {%><%= livro.getNome() %> <%}%>">

  <label for="data">Data:</label>
  <input required type="date" id="data" name="livro_data" value="<% if(temLivro) {%><%=livro.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))%><%}%>">

  <label for="autor">Autor:</label>
  <select required id="autor" name="livro_autor">
    <%if (!temLivro) {%><option value="" disabled selected>Selecione uma opção</option> <%}%>
    <% for (Autores autor : autores) { %>
    <option value="<%=autor.getId()%>"
      <%if (temLivro) { if (livro.getAutor().getId() == autor.getId()) {%> selected <%} }%>><%=autor.getNome()%></option>
    <%}%>
  </select>

  <label for="status">Status:</label>
  <select required id="status" name="livro_status">
    <option value="DISPONIVEL" <% if(temLivro) { if (livro.getStatus() == LivroStatus.DISPONIVEL) { %>selected<% } }%>>DISPONIVEL</option>
    <option value="EMPRESTADO" <% if(temLivro) { if (livro.getStatus() == LivroStatus.EMPRESTADO) { %>selected<% } }%>>EMPRESTADO</option>
    <option value="INDISPONIVEL" <% if(temLivro) { if (livro.getStatus() == LivroStatus.INDISPONIVEL) { %>selected<% } }%>>INDISPONIVEL</option>
  </select>

  <button type="submit" class="button-green">Salvar</button>
  <a type="button" class="button-red"
          href="<%=LocalHost.redirectLivros%>">Cancelar</a>
  <%if (request.getAttribute("erro") != null) { %>
  <p style="color: red"><%=request.getAttribute("erro")%></p>
  <% } else if (request.getParameter("erro") != null) {%>
  <p style="color: red"><%=request.getParameter("erro")%></p>
  <% } %>
</form>
</body>
</html>
