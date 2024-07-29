<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cst8288.project.fwrp.model.User"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">

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
				} else if (!request.getServletPath().contains(user.getType().name().toLowerCase())) {
				%>
				<%
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				%>
				<%
				} else {
				%>
				<li class="nav-item">
				
<%-- 				<a class="nav-link " aria-current="page"
					href="${pageContext.request.contextPath}/pages/consumer/index.jsp">Shopping
						List</a>
 --%>						
					<form action="${pageContext.request.contextPath}/retailers/items/all"
						method="GET">
						<button type="submit" class="btn btn-link">Home</button>
					</form>
						
						</li>
<%-- 				<li class="nav-item">
					<form action="${pageContext.request.contextPath}/retailers/surplus"
						method="GET">
						<input type="hidden" name="user_id" value="<%=user.getId()%>">
						<button type="submit" class="btn btn-link">Surplus
							Items</button>
					</form>
				</li>
 --%>
<%-- 				<li class="nav-item">
					<form
						action="${pageContext.request.contextPath}/retailers/dontations"
						method="GET">
						<button type="submit" class="btn btn-link">Donation
							Item</button>
					</form>
				</li> --%>
	<li class="nav-item">
		
		<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#create_modal">
New Item
</button>

<%-- <!-- Modal -->
<div class="modal fade" id="create_modal" tabindex="-1"
	aria-labelledby="create_modal_label" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="create_modal_label">Modal
					title</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form action="${request.contextPath}/retailers/items/create">
					<input type="hidden" name="id">
					<div class="mb-3">
						<label for="name" class="form-label"> Product Name</label> <input
							type="text" name="name" class="form-control" id="name">
					</div>
					<div class="mb-3">
						<label for="description" class="form-label"> Product
							Description </label> <input type="text" name="description"
							class="form-control" id="description">
					</div>
					<div class="mb-3">
						<label for="price" class="form-label"> Price($) </label> <input
							type="number" name="price" class="form-control" id="price" min="0" step=".01">
					</div>
					<div class="mb-3">
						<label for="discount_rate" class="form-label"> Discount
							Rate (%)</label> <input type="number" name="discountRate"
							class="form-control" id="discount_rate" min="0" max="100">
					</div>
					<div class="mb-3">
						<label for="quantity">Quantity:</label> <input type="number"
							name="quantity" class="form-control" min="1" required>
					</div>

					<div class="mb-3">
						<label for="expiry_date">Expiry Date:</label> <input type="date"
							name="expiryDate" class="form-control" min="1" required>
					</div>

					<div class="mb-3">
						<label for="is_surplus" class="form-check-label">Is it a
							Surplus Item?:</label> <input type="checkbox" name="is_surplus"
							class="form-check-input" >
					</div>

					<div class="mb-3">
						<label for="is_donation" class="form-check-label">Is it
							for Donation?:</label> <input type="checkbox" name="is_donation"
							class="form-check-input" >
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Create</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
 --%>  <c:import url="/fragments/retailer/create_modal.jsp" /> 
				</li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>