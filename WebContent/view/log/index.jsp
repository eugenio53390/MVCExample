<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% List<Log> logs = (ArrayList<Log>) request.getAttribute("logs"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Logs</title>
</head>
<body>
	<div>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Date/time</th>
				<th>Actions</th>
				<th>Note</th>
				<th>Id user</th>
			</tr>
		</thead>
		<tbody>
			<%for(Log log:logs) {%>
				<tr>
					<td><%=log.getId() %></td>
					<td><%=log.getCreation_log() %></td>
					<td><%=log.getActions() %></td>
					<td><%=log.getNote() %></td>
					<td><%=log.getId_user() %></td>
				</tr>
			<%} %>
		</tbody>
	</table>
	</div>
</body>
</html>