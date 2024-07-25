<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- <link href="${contextPath}/resource/bootstrap.min.css" rel="stylesheet">  --%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Adam, Tony, Josh, David">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/global.css"
	type="text/css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Food Waste Reduction Project</title>
</head>
<body>
	<main id="index-main">
		<section id="welcome-message">
			<img src="${pageContext.request.contextPath}/asset/logo_trans.png"
				id="index-logo" />
		</section>
		<form class="card p-3" action="auth/login" method="POST"
			id="login-form">
			<div class="card-body">
				<div class="mb-3 form-floating">
					<input
						class="form-control" id="email" type="email" name="email"
						placeholder="account email" required />
					<label for="email" >Email address</label> 
				</div>
				<div class="mb-3 form-floating">
					 <input
						class="form-control" id="password" type="password" name="password"
						placeholder="account password" minlength="8" />
					<label for="password" >Password</label>
				</div>
				<%
				String err = (String) request.getSession().getAttribute("errMsg");
				%>
				<%
				if (err != null) {
				%>
				<span class="err-msg"><%=err%> </span>
				<%
				}
				%>
				<div class="mx-auto">
					<button type="submit" class="btn btn-primary">Login</button>
					<button type="reset" class="btn btn-secondary">Cancel</button>
				</div>
				<a class="card-link"
					href="${pageContext.request.contextPath}/pages/register.jsp">No
					Account? Click Here To Register</a>

			</div>
		</form>
		<footer class="mt-5 text-center">

			<h3>Welcome to the Food Waste Reduction Project!</h3>
			<h4>Let's work together to reduce food waste.</h4>
		</footer>
	</main>
</body>
</html>
