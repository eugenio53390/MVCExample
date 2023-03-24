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
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>username</th>
				<th>first_name</th>
				<th>last_name</th>
				<th>email</th>
				<th>cellphone</th>
				<th>birth_date</th>
				<th>date_access</th>
				<th>date_insert</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		
			<%for(User user:users) {%>
				<tr>
					<td><%=user.getUsername() %></td>
					<td><%=user.getFirst_name() %></td>
					<td><%=user.getLast_name() %></td>
					<td><%=user.getEmail() %></td>
					<td><%=user.getCellphone() %></td>
					<td><%=user.getBirth_date() %></td>
					<td><%=user.getDate_access() %></td>
					<td><%=user.getDate_insert() %></td>
					<td><a href="UsersEdit/?action=EDIT&id=<%=user.getId() %>">EDIT</a>&nbsp;<a href="UsersEdit/?action=DELETE&id=<%=user.getId() %>">DELETE</a></td>
				</tr>
			<%}%>
		</tbody>
	</table>
</body>
</html>