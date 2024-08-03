<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="cst8288.project.fwrp.model.Item"%>
<%@ page import="cst8288.project.fwrp.model.User"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>



<c:import url="/includes/header.jsp" />
<c:import url="/includes/session_checker.jsp" />

<%
User user = (User) request.getSession().getAttribute("user");
%>
<%-- <%
if (user == null) {
%>
<%
response.sendRedirect(request.getContextPath() + "/index.jsp");
%>
<%
} else if (!request.getServletPath().contains(user.getType().name().toLowerCase())) {
%>

<%
response.sendRedirect(request.getContextPath() + "/index.jsp");
%>
<%
} else {
%> --%>

<main>
<% String msg = (String)  request.getAttribute("msg"); %>
<% if (msg != null && msg.contains("successfully")) {%> 
<p class="text-success"> <%= msg %></p>
<% } else { %>

<p class="text-danger"> <%= msg %></p>
<%} %> 
	<form class="card wx-75 mx-auto py-2 px-5" style=""
		action="${pageContext.request.contextPath}/users/update" method="POST"
		id="login-form">
		<div class="card-body wx-75">
			<h2 class="card-title">Update Profile</h2>
			<input type="hidden" name="id" value="<%=user.getId()%>" />
			<div class="mb-2 form-floating">
				<input class="form-control" id="name" type="text" name="name"
					value="<%=user.getName()%>" placeholder="name" required /> <label
					for="name">Name</label>
			</div>
			<div class="mb-2 form-floating">
				<input class="form-control" id="email" type="email" name="email"
					value="<%=user.getEmail()%>" placeholder="account email" required />
				<label for="email">Email Address</label>
			</div>
			<div class="mb-2  form-floating">
				<input class="form-control" id="password" type="password"
					value="<%=user.getPassword()%>" name="password"
					placeholder="password" minlength="8" required> <label
					for="password">Password</label>
			</div>
			<div class="mb-2 form-floating">
				<input id="phone" class="form-control py-2" type="text" name="phone"
					value="<%=user.getPhone() != null ? user.getPhone() : ""%>"
					placeholder="e.g: +12344567890 (optional)" /> <label for="phone">Phone</label>
				<small>e.g: +12344567890</small>
			</div>

			<div class="mb-2">
				<select id="commMethod" name="commMethod" class=" form-select">
					<option value="Email"
						<%=user.getCommMethod() == null ? "" : user.getCommMethod().name().equals("Email") ? "selected" : ""%>>Email</option>
					<option value="Phone"
						<%=user.getCommMethod() == null ? "" : user.getCommMethod().name().equals("Phone") ? "selected" : ""%>>Phone</option>
					<option value="Both"
						<%=user.getCommMethod() == null ? "" : user.getCommMethod().name().equals("Both") ? "selected" : ""%>>Both</option>
				</select> <small>Preferred Communication Method</small>
			</div>

			<div class="mb-3 form-floating">
				<input id="location" class="form-control" type="text"
					name="location" placeholder="city" required
					value="<%=user.getLocation()%>" /> <label for="location">City</label>
			</div>

			<div class="mx-auto">
				<button type="submit" class="btn btn-primary">Update</button>
			</div>
		</div>
	</form>
<%-- 	<%
	}
	%> --%>
</main>


	<div class="back-btn">
		<a href="${pageContext.request.contextPath}/pages/consumer/index.jsp">
			<button class="btn btn-primary">Back</button>
		</a>

	</div>




<c:import url="/includes/footer.jsp" />