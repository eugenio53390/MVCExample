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
</head>
<body>
	<div align="center">
        <form action="UserEdit" method="post">
        	<input type="hidden" name="id" value="<%=user.getId() %>">
        
            <table style="width: 80%">
            	<tr>
                    <td>Username</td>
                    <td><input type="text" required="required" name="username" value="<%=user.getUsername() %>"/></td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" required="required" name="first_name" value="<%=user.getFirst_name() %>"/></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" required="required" name="last_name" value="<%=user.getLast_name() %>"/></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" required="required" name="email" value="<%=user.getEmail() %>"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" required="required" name="password" value="<%=user.getPassword() %>"/></td>
                </tr>
                <tr>
                    <td>Cellphone</td>
                    <td><input type="text" required="required" name="cellphone" value="<%=user.getCellphone() %>"/></td>
                </tr>
                <tr>
                    <td>Birth date</td>
                    <td><input type="date" required="required" name="birth_date" value="<%=user.getBirth_date() %>"/></td>
                </tr>
                <tr>
                    <td>Date access</td>
                    <td><input type="datetime" name="date_access" readonly="readonly" value="<%=user.getDate_access() %>"/></td>
                </tr>
                <tr>
                    <td>Date insert</td>
                    <td><input type="date" name="date_insert" readonly="readonly" value="<%=user.getDate_insert() %>"/></td>
                </tr>
            </table>
            <input type="submit" value="Submit" />
        </form>
    </div>
</body>
</html>