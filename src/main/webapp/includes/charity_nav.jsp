<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cst8288.project.fwrp.model.User"%>
<%@ page import="cst8288.project.fwrp.model.UserType"%>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
<%-- 		<a class="nav-link active" aria-current="page"
			href="${pageContext.request.contextPath}/pages/charity/index.jsp">Home</a>
 --%>	
 	<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item">
					<form class=""
						action="${pageContext.request.contextPath}/charitable_orgs/items/all">
						<button type="submit" class="btn btn-link">Home</button>
					</form>
				</li>
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
				} else if (!request.getServletPath().contains("charit")) {
				%>
				<%
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				%>
				<%
				} else {
				%>
				<li class="nav-item">
<%-- 					<form
						action="${pageContext.request.contextPath}/consumers/subscribe"
						method="GET">
						<input type="hidden" name="user_id" value="<%=user.getId()%>">
						<button type="submit" class="btn btn-link">Subscribed To</button>
					</form>

 --%>				</li>
				<li class="nav-item">
					<form
						action="${pageContext.request.contextPath}/charitable_orgs/claim_history"
						method="GET">
						<input type="hidden" name="user_id" value="<%=user.getId()%>">
						<button type="submit" class="btn btn-link">Order History</button>
					</form>
				</li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>