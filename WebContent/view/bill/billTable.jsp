
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@page import="dao.BillDao, model.Bill, model.BillRow, java.util.ArrayList"%>
<%@page import="dao.BillRowDao" %>
<%@page import="java.util.List"%>
    <% 
	List<Bill> list=(ArrayList<Bill>) request.getAttribute("bills");%>
<!DOCTYPE html>
<html>
<style>
@font-face{
	font-family: Jurassic;
	src: url("Font/Jurassic Park.ttf");
}
table, th, td {
  border:1px solid black;
}
h1 {
	text-align: center;
}
.jurassic{font-family: Jurassic; font-size: 32mm;}
</style>
<head>
	<meta charset="ISO-8859-1">
	<title>Fatture</title>
	<link rel="icon" type="image/x-icon" href="Icon/SEO-illustrations-Invoicing-01.png">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body class="bg-dark container mt-1">
<h1 style="color: #d4af37;" class="h1 jurassic">BILL VIEW</h1>
	<table style="width:100%" class="table table-dark table-bordered table-hover">
		<thead>
			<th>id</th>
			<th>description</th>
			<th>date_insert</th>
			<th>user</th>
		</thead>
		<tbody>
		<% for(Bill bill: list){ %>
			<tr>
				<td><%=bill.getId()%></td>
				<td><%=bill.getDescription()%></td>
				<td><%=bill.getDate_insert()%></td>
				<td><%=bill.getUser().getFirst_name()+" "+bill.getUser().getLast_name()%></td>
			</tr>
		<%  } %>
		</tbody>
	</table>
</body>
</html>