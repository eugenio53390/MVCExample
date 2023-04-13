<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User user = (User) request.getAttribute("user"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="p-5">
	<a href="UsersIndex"><button type="button" class="btn btn-warning mr-3">TORNA ALLA LISTA</button></a>
  	<h1 class="fw-bold mt-3"><span class="text-primary">MODIFICA/AGGIUNGI</span> UTENTE</h1>
    
    <form action="UsersEdit" method="post">
  <div class="mb-3">
    <label for="username" class="form-label fw-bold">Username:</label>
    <input type="text" class="form-control" id="username" value="<%=user.getUsername() %>">
  </div>
  <div class="mb-3">
    <label for="nome" class="form-label fw-bold">Nome:</label>
    <input type="text" class="form-control" id="nome" value="<%=user.getFirst_name() %>">
  </div>
  <div class="mb-3">
    <label for="cognome" class="form-label fw-bold">Cognome:</label>
    <input type="text" class="form-control" id="cognome" value="<%=user.getLast_name() %>">
  </div>
  <div class="mb-3">
    <label for="email" class="form-label fw-bold">Email:</label>
    <input type="text" class="form-control" id="email" value="<%=user.getEmail() %>">
  </div>
  <div class="mb-3">
    <label for="password" class="form-label fw-bold">Password:</label>
    <input type="text" class="form-control" id="password" value="<%=user.getPassword() %>" readonly="readonly">
  </div>
  <div class="mb-3">
    <label for="num_cell" class="form-label fw-bold">Numero di cellulare:</label>
    <input type="text" class="form-control" id="num_cell" value="<%=user.getCellphone() %>">
  </div>
  <div class="mb-3">
    <label for="data_nascita" class="form-label fw-bold">Data di nascita:</label>
    <input type="text" class="form-control" id="data_nascita" value="<%=user.getBirth_date() %>">
  </div>
  <div class="mb-3">
    <label for="ultimo_accesso" class="form-label fw-bold">Data ultimo accesso:</label>
    <input type="text" class="form-control" id="ultimo_accesso" value="<%=user.getDate_access() %>">
  </div>
  <div class="mb-3">
    <label for="data_inserimento" class="form-label fw-bold">Data di inserimento:</label>
    <input type="text" class="form-control" id="data_inserimento" value="<%=user.getDate_insert() %>">
  </div>
  <div class="mb-3">
    <label for="ruolo" class="form-label fw-bold">Ruolo:</label>
    <input type="text" class="form-control" id="ruolo" value="<%=user.getRole() %>">
  </div>
  <button type="reset" class="btn btn-warning">svuota i campi</button>
  <button type="submit" class="btn btn-success">salva</button>
</form>
</body>
</html>