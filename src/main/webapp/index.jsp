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
				id="header-logo" />
			<h2>Welcome to the Food Waste Reduction Project!</h2>
			<h3>Let's work together to reduce food waste.</h3>
		</section>
		<form action="auth/login" method="POST" id="login-form">
			<input type="email" name="email" placeholder="account email" required />
			<input type="password" name="password" placeholder="account password"
				minlength="8" />
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
			<div class="btn-group">
				<button type="submit" class="btn btn-warning">Login</button>
				<button type="reset" class="btn btn-danger">Cancel</button>
			</div>
		</form>
		<a href="${pageContext.request.contextPath}/pages/register.jsp">No
			Account? Here to Register</a>
	</main>
</body>
</html>
