<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cst8288.project.fwrp.model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to Charitable Organization Page!</h1>
	<%
	if (request.getSession() != null) {
	%>
	<%
	User user = (User) request.getSession().getAttribute("user");
	%>
	<p>Hello! Welcome back! ${user.getName()}</p>
	<%
	}
	%>
</body>
</html>