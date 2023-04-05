<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<title>Login</title>
<%@include file="/view/structure/head.jsp" %>
</head>
<body class="h-100">
<div class="container h-100">
<main class="d-flex justify-content-center h-100 align-items-center">
		<div class="card">
			<div class="card-body p-4 p-md-5">
				<form action="login" method="post" data-bitwarden-watching="1">
					<h1 class="text-center">Login</h1>

					<div class="mb-3">
						<label for="username" class="form-label">Username</label>
						<input type="text" class="form-control" required autofocus name="username" id="username" placeholder="">
					</div>

					<div class="mb-3">
						<label for="password" class="form-label">Password</label>
						<input type="password" class="form-control" required name="password" id="password" placeholder="">
					</div>
					<p class="text-danger text-center">
											</p>
					<button class="btn btn-primary w-100">
						Accedi
					</button>
				</form>
			</div>
		</div>
	</main>
</div>
</body>
</html>