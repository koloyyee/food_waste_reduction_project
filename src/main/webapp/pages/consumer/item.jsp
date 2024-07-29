<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="cst8288.project.fwrp.model.Item"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/includes/header.jsp" />
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
				<p>
					Description:
					<%=item.getDescription()%></p>
				<p>
					Stock level:
					<%=item.getQuantity()%></p>
				<p>
					Original Price:
					<del>
						$<%=item.getOriginalPrice()%></del>
				</p>
				<p>
					Discount:
					<%=item.getDiscountRate() * 100%>% off!
				</p>
				<p>
					Price: $<%=item.getDiscountedPrice()%></p>

				<form
					action="${pageContext.request.contextPath}/consumers/items/order"
					method="POST">
					<label for="quantity">Quantity:</label> <input type="number"
						name="quantity" min="1" max="<%=item.getQuantity()%>" required>
					<input type="hidden" name="id" value="<%=item.getId()%>"> <input
						type="hidden" name="discounted_price" value="<%=item.getDiscountedPrice()%>">
					<button type="submit" class="btn btn-primary">Order it
						now!</button>
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

<!-- 		<div class="back-btn">
			<button class="btn btn-primary" type="button"
				onclick="history.back()">Back</button>
		</div>
 -->
 
 </main>
<c:import url="/includes/footer.jsp" />