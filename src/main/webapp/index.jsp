<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
   <c:import url="header.html" />

<jsp:include page="header.html"/>
	<form action="users/register" method="POST">
		<input type="text" name="name" />
		<button type="submit">submit</button>
	</form>
	<nav>
		<ul>
			<li><a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a></li>
			<li><a
				href="${pageContext.request.contextPath}/pages/register.jsp">Register</a></li>
		</ul>
	</nav>
</body>
</html>