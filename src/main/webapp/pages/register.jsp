<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Adam, Tony, Josh, David">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/global.css"
	type="text/css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Food Waste Reduction Project</title>
</head>
<body>
	<main id="">
		<section id="welcome-message">
			<img src="${pageContext.request.contextPath}/asset/logo_trans.png"
				id="header-logo" />
			<h2>Welcome to the Food Waste Reduction Project!</h2>
			<h3>Let's work together to reduce food waste.</h3>
		</section>
		<%
String errMsg = (String) request.getAttribute("errMsg");
%>
		<%
		if (errMsg != null) {
		%>
		<p class="err-msg">${errMsg }</p>
		<%
}
%>
		<form class="card wx-75 mx-auto py-2 px-5" style="max-width"
			action="${pageContext.request.contextPath}/users/register"
			method="POST" id="login-form">
			<div class="card-body wx-75">
			    <h2 class="card-title">Register</h2>
				<div class="mb-2 form-floating">
					<input class="form-control"  type="text" name="name" placeholder="name" required /> <label
						for="name">Name</label>
				</div>
				<div class="mb-2 form-floating">
					<input class="form-control"  type="email" name="email" placeholder="account email"
						required /> <label for="email">Email Address</label>
				</div>
				<div class="mb-2  form-floating">
					<input class="form-control"  type="password" name="password" placeholder="password"
						minlength="8" required> <label for="password">Password</label>
				</div>
				<div class="mb-2 ">
					<select id="userType" name="type" class=" form-select">
						<option value="Consumer">Consumer</option>
						<option value="Retailer">Retailer</option>
						<option value="CharitableOrg">Charitable Organization</option>
					</select>
				</div>
				<div class="mb-2 form-floating">
					<input class="form-control py-2"  type="text" name="phone" placeholder="e.g: +12344567890 (optional)" /> 
					<label for="phone">Phone</label>
					<small>e.g: +12344567890</small>
				</div>

				<div class="mb-2">
					<select id="commMethod" name="commMethod"  class=" form-select">
						<option value="Email">Email</option>
						<option value="Phone">Phone</option>
						<option value="Both">Both</option>
					</select>
				<small>Preferred Communication Method</small> 
				</div>

				<div class="mb-3 form-floating">
					<input class="form-control" type="text" name="location" placeholder="city" required />
					<label for="location">City</label>
				</div>
				<!-- 		 <input
			id="orgName" type="text" name="org_name"
			placeholder="company or organization name">
 -->
				<div class="mx-auto">
					<button type="submit" class="btn btn-primary">Register</button>
					<button type="reset" class="btn btn-secondary">Cancel</button>
				</div>

			</div>
		</form>
		<a href="${pageContext.request.contextPath}/"> Have an Account?
			Click here to Login</a>
	</main>
</body>
<script>
	const orgName = document.getElementById("orgName");
	window.onload = function() {
		orgName.style.display = "none";
	};
	const userType = document.getElementById("userType");
	userType.addEventListener('change',
			function() {
				if (userType.value === "CharitableOrg"
						|| userType.value === "Retailer") {
					orgName.style.display = "block";
				} else {
					orgName.style.display = "none";
				}
			});
</script>
</html>