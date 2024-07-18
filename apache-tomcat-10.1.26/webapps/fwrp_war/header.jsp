<%@page import="cst8288.project.fwrp.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Adam, Tony, Josh, David">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" type="text/css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Food Waste Reduction Project</title>
</head>
<body>
<nav class="d-flex justify-content-between">
	
			<img src="${pageContext.request.contextPath}/asset/logo_trans.png"
				id="header-logo" />
			<%  User user =  (User) request.getSession().getAttribute("user"); %>
			<% user.getType(); %>
			<% if( user != null ) { %>
				<form action="${pageContext.request.contextPath}/auth/logout" method="POST">
					<button class="btn btn-warning"> Logout </button>	
				</form>
			<%} else if ( request.getAttribute("errMsg") == null ) {%>
				<% response.sendRedirect(request.getContextPath() + "/index.jsp"); %>
			<%} %> 
			


</nav>