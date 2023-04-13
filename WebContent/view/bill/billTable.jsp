
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@page import="dao.BillDao, model.Bill, model.BillRow, java.util.ArrayList"%>
<%@page import="dao.BillRowDao" %>
<%@page import="java.util.List"%>
<% 
	List<Bill> list=(ArrayList<Bill>) request.getAttribute("bills");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Fatture</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="p-5">
	<a href="home"><button type="button" class="btn btn-warning mr-3">HOME</button></a>
	<h1 class="mt-3 fw-bold">LISTA <span class="text-primary">FATTURE</span></h1>
	<table class="table">
		<thead>
			<th>id</th>
			<th>descrizione</th>
			<th>data inserimento</th>
			<th>utente</th>
		</thead>
		<tbody>
		<% if(!list.isEmpty()) {
			for(Bill bill: list){ %>

			<tr>
				<td><%=bill.getId()%></td>
				<td><%=bill.getDescription()%></td>
				<td><%=bill.getDate_insert()%></td>
				<td><%=bill.getUser().getFirst_name()+" "+bill.getUser().getLast_name()%></td>
			</tr>
		<% }} %>
		</tbody>
	</table>
</body>
</html>