<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- <link href="${contextPath}/resource/bootstrap.min.css" rel="stylesheet"> --%>

<c:import url="/header.jsp" />
<main id="index-main">
	<section id="welcome-message">
		<img src="${pageContext.request.contextPath}/asset/logo_trans.png" id="header-logo" />
		<h2>Welcome to the Food Waste Reduction Project!</h2>
		<h3>Let's work together to reduce food waste.</h3>
	</section>
	<form action="users/login" method="POST" id="login-form">
		<input type="email" name="email" placeholder="account email" required />
		<input type="password" name="password" placeholder="account password"
			minlength="8" />
		<div class="btn-group">
			<button type="submit">Login</button>
			<button type="reset" class="btn-cancel">Cancel</button>
		</div>
	</form>
	<a href="${pageContext.request.contextPath}/pages/register.jsp">No
		Account? Here to Register</a>
</main>
</body>
</html>
