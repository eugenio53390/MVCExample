<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	User u = (User)session.getAttribute("session_user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Bootstrap demo</title>
</head>
<body>
<main class="d-flex justify-content-center h-100 align-items-center">
		<div class="card px-4 py-5 my-5 text-center">
			<h1 class="display-5 fw-bold text-body-emphasis">Benvenuto, <span class="text-primary"><%= u.getUsername() %></span></h1>
			<div class="col-lg-6 mx-auto">
		      <p class="lead mb-4">Ti trovi ora nella tua area privata. scegli l'operazione che indendi esegure.</p>
		      <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
		       <a href="UsersIndex"><button type="button" class="btn btn-primary">Gestione degli utenti</button></a>
		       <a href="BILLIndex"><button type="button" class="btn btn-primary">Gestione delle fatture</button></a>
		      </div>
		    </div>
		</div>
	</main>
</body>
</html>