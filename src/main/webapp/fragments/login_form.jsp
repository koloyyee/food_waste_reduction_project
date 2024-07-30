<form class="card p-3" action="auth/login" method="POST"
	  id="login-form">
	<div class="card-body">
		<div class="mb-3 form-floating">
			<input
					class="form-control" id="email" type="email" name="email"
					placeholder="account email" required />
			<label for="email" >Email address</label>
		</div>
		<div class="mb-3 form-floating">
			<input
					class="form-control" id="password" type="password" name="password"
					placeholder="account password" minlength="8" required />
			<label for="password" >Password</label>
		</div>
		<%
			String err = (String) request.getSession().getAttribute("errMsg");
		%>
		<%
			if (err != null) {
		%>
		<span class="err-msg"><%=err%> </span>
		<%
			}
		%>
		<div class="mx-auto">
			<button type="submit" class="btn btn-primary">Login</button>
			<button type="reset" class="btn btn-secondary">Cancel</button>
		</div>
		<a class="card-link"
		   href="${pageContext.request.contextPath}/pages/register.jsp">No
			Account? Click Here To Register</a>

	</div>
</form>
