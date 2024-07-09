<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(request.getAttribute("userName") != null) { 
	String name = (String) request.getAttribute("userName");
	%>
	<%= name %>

<% } %>
<%= request.getAttribute("userName")  %>
Hello
</body>
</html>