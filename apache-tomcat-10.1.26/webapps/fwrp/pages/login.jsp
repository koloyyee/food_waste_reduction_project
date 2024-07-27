<%@page import="cst8288.project.fwrp.model.User"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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



		<%
		User user = (User) request.getSession().getAttribute("user");
		%>
		<%
		if (user == null) {
		%>
		<%
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		%>
		<%
		} else if (!request.getServletPath().contains(user.getType().name().toLowerCase())) {
		%>
		<%
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		%>
		<%
		}
		%>
		<%
		if (user != null) {
		%>

	<nav class="d-flex justify-content-between">
		<a href="${pageContext.request.contextPath}/pages/${user.getType().name().toLowerCase()}/index.jsp"> <img
			src="${pageContext.request.contextPath}/asset/logo_trans.png"
			id="header-logo" />
		</a>

		<div class="d-inline-flex" style="gap: 1.3rem">
			<p>${user.getName()}</p>
			<form action="${pageContext.request.contextPath}/auth/logout"
				method="POST">
				<button class="btn btn-secondary">Logout</button>
			</form>
		</div>
	</nav>
		<%
		}
		%>