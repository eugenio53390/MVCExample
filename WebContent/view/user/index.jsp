<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% List<User> users = (ArrayList<User>) request.getAttribute("users"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Users</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="p-5">
	<a href="home"><button type="button" class="btn btn-warning mr-3">HOME</button></a>
	<a href="UsersEdit?action=INSERT"><button type="button" class="btn btn-primary">INSERISCI NUOVO</button></a>	
	
	<h1>LISTA UTENTI REGISTRATI</h1>
	<br>
	<form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
      <br>	

	
  <table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">id</th>
      <th scope="col">username</th>
      <th scope="col">nome</th>
      <th scope="col">cognome</th>
      <th scope="col">email</th>
      <th scope="col">numero cellulare</th>
      <th scope="col">data nascita</th>
      <th scope="col">ultimo accesso</th>
      <th scope="col">data registrazione</th>
      <th scope="col">ruolo</th>
    </tr>
  </thead>
  <tbody>
    <tbody>
		
			<%for(User user:users) {%>
				<tr>
					<td><%=user.getId() %></td>
					<td><%=user.getUsername() %></td>
					<td><%=user.getFirst_name() %></td>
					<td><%=user.getLast_name() %></td>
					<td><%=user.getEmail() %></td>
					<td><%=user.getCellphone() %></td>
					<td><%=user.getBirth_date() %></td>
					<td><%=user.getDate_access() %></td>
					<td><%=user.getDate_insert() %></td>
					<td><%=user.getRole() %></td>
					<td><a href="UsersEdit?action=EDIT&id=<%=user.getId() %>"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
  <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
</svg></a>&nbsp;<a href="UsersEdit?action=DELETE&id=<%=user.getId() %>"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
  <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
</svg>	</a></td>
				</tr>
			<%}%>
		</tbody>
  </tbody>
</table>
</body>
</html>