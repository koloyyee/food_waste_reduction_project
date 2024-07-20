<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="cst8288.project.fwrp.model.Item"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/header.jsp" />
<!-- 
view item detail. Consumer can order item from here. so view, and create
order.
 -->
<%
Item item = (Item) request.getAttribute("item");
%>
<%
if (item != null) {
%>
<main>
	<div class="card">
		<div class="card-header">
			<h3>
				<%=item.getName()%>
			</h3>
		</div>
		<div class="card-body">
			<blockquote class="blockquote mb-0">
				<%=item.toString()%>
				<form class="form-group"
					action="${pageContext.request.contextPath}/retailers/items/order/${requestScope.item.getId()}"
					method="POST">
					<input type="hidden" name="id" value="<%=item.getId()%>"> <label
						for="description"> Product Name </label>
					<div class="mb-3">
						<label for="name" class="form-label"> Product Name</label> <input
							type="email" name="name" class="form-control" id="description"
							value="${item.getName() }">
					</div>
					<div class="mb-3">
						<label for="description" class="form-label"> Product
							Description </label> <input type="text" name="decription"
							class="form-control" id="description"
							value="${item.getDescription() }">
					</div>
					<div class="mb-3">
						<label for="price" class="form-label"> Price </label> <input
							type="number" name="price" class="form-control" id="price"
							value="${item.getOriginalPrice() }">
					</div>
					<div class="mb-3">
						<label for="discount_rate" class="form-label"> Discount
							Rate</label> <input type="number" name="discountRate"
							class="form-control" id="discount_rate"
							value="${item.getDiscountRate() * 100 }">
					</div>
					<div class="mb-3">
						<label for="quantity">Quantity:</label> <input type="number"
							name="quantity" class="form-control" min="1"
							max="<%=item.getQuantity()%>" required>
					</div>
					<div class="mb-3">
						<label for="" class="form-label"> Product Description </label> <input
							type="number" name="discountRate" class="form-control"
							id="discount_rate" value="${item.getDiscountRate() * 100 }">
					</div>
					<div class="form-check mb-3">
						<input class="form-check-input" type="checkbox" value="${item.isSurplus()}"
							id="is_surplus"<c:if test="${item.isSurplus()}">checked=checked</c:if>> <label class="form-check-label"
							for="is_surplus">Mark as Surplus </label>
					</div>
					<div class="form-check mb-3">
						<input class="form-check-input" type="checkbox" value="${item.isDonation() }"
							id="is_donation" <c:if test="${item.isDonation()}">checked=checked</c:if>>
							 <label class="form-check-label" for="is_donation"> Mark as Donation</label>
					</div>
					<input type="hidden" name="price" value="<%=item.getPrice()%>">
					<!-- 					<button type="submit" class="btn btn-primary">Update
						Quantity</button>
 -->
				</form>
			</blockquote>
		</div>
		<%
		}
		%>

	</div>
	<%
	String errMsg = (String) request.getAttribute("errMsg");
	%>
	<%
	if (errMsg != null) {
	%>
	<div class="alert alert-danger" role="alert">
		<%=errMsg%>
	</div>

	<%
	}
	%>

	<div class="back-btn">
		<a href="${pageContext.request.contextPath}/pages/consumer/index.jsp">
			<button class="btn btn-primary">Back</button>
		</a>

	</div>
</main>
</body>
</html>