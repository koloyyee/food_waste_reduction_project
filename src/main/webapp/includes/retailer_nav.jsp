<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cst8288.project.fwrp.model.User"%>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="nav-link active" aria-current="page"
			href="${pageContext.request.contextPath}/pages/consumer/index.jsp">Home</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link " aria-current="page"
					href="${pageContext.request.contextPath}/pages/consumer/index.jsp">Shopping
						List</a></li>
					<%
					User user = (User) request.getSession().getAttribute("user");
					%> <%
 if (user == null) {
 %> <%
 response.sendRedirect(request.getContextPath() + "/index.jsp");
 %> <%
 } else if (!request.getServletPath().contains(user.getType().name().toLowerCase())) {
 %> <%
 response.sendRedirect(request.getContextPath() + "/index.jsp");
 %> <%
 } else {
 %>
 	
<%-- 				<li class="nav-item">
					<form
						action="${pageContext.request.contextPath}/retailer/surplus"
						method="GET">
						<input type="hidden" name="user_id" value="<%=user.getId()%>">
						<button type="submit" class="btn btn-primary"> Surplus Items</button>
					</form> 
					</li>

					<li class="nav-item">
					<form
						action="${pageContext.request.contextPath}/retailer/dontations"
						method="GET">
						<button type="submit" class="btn btn-primary"> Donation Item</button>
					</form> 
					</li>
 --%>					
<%
 }
 %>
			</ul>
		</div>
	</div>
</nav>