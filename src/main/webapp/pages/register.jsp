<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/header.jsp" />
<%-- <c:set var="contextPath" value="${pageContext.request.contextPath}" /> --%>
<main>

	<section id="welcome-message">
		<img src="${pageContext.request.contextPath}/asset/logo_trans.png"
			id="header-logo" />
		<h2>Welcome to the Food Waste Reduction Project!</h2>
		<h3>Let's work together to reduce food waste.</h3>
	</section>
	<% String errMsg =  (String) request.getAttribute("errMsg"); %>
	<% if (errMsg != null ) { %>
		<p class="err-msg"> ${errMsg } </p>
	<% } %>
	<%= errMsg %>

	<form action="${pageContext.request.contextPath}/users/register" method="POST" id="login-form">
		<input type="text" name="name" placeholder="name" required />
		<input type="email" name="email" placeholder="account email" required />
		<input type="password" name="password" placeholder=password" minlength="8" required> 
			
			<select  name="type">
			<option value="Consumer">Consumer</option>
			<option value="Retailer">Retailer</option>
			<option value="CharitableOrg">Charitable Organization</option>
		</select> <input type="text" name="phone" placeholder="phone(optional)" />
		<div class="btn-group">
			<button type="submit">Register</button>
			<button type="reset" class="btn-cancel">Cancel</button>
		</div>
	</form>
	<a href="${pageContext.request.contextPath}/"> Has Account? Here to
		Login</a>
</main>
</body>
</html>