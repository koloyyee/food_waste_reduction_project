<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cst8288.project.fwrp.model.User"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/header.jsp" />
	<h1>Welcome to Retailer Page!</h1>
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
	<form action="retailers/hello">
		<input type="text" name="test" > 
		<button type="submit"> submit </button>
	</form>
</body>
</html>