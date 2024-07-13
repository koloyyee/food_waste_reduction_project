<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="cst8288.project.fwrp.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(request.getSession() != null) { 
/* 	User user = (User) request.getAttribute("user");
 */	%>
	<%= request.getSession().getAttribute("user") %>

<% } %>
Hello
</body>
</html>