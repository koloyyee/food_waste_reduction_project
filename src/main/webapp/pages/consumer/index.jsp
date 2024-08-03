<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cst8288.project.fwrp.model.User"%>
<%@ page import="cst8288.project.fwrp.model.Item"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/consumer_nav.jsp" />
<main>
	<h1>Welcome to Consumer Page!</h1>
	<%
User user = (User) request.getSession().getAttribute("user");
%>
	<%
List<Item> items = (List<Item>) request.getSession().getAttribute("items");
%>

	<%
	if (items != null && items.size() > 0) {
	%>
	<!-- create table -->
	<table class="table">
		<tr>
			<th scope="col">Item Name</th>
			<th scope="col">Item Description</th>
			<th scope="col">Item Quantity</th>
			<th scope="col">Item Price</th>
		</tr>
		<%
		for (Item item : items) {
		%>
		<tr>

			<%
			String surplusMsg = item.isSurplus() ? "(Expiring Soon!)" : "";
			%>
			<%
			String discountMsg = item.getDiscountRate() > 0 ? "(Discounted Item!)" : "";
			%>

			<td><%=item.getName()%> <span style="color: red"> <%=surplusMsg%>
					<%=discountMsg%>
			</span></td>
			<td><%=item.getDescription()%></td>
			<td><%=item.getQuantity()%></td>
			<td>$<%=item.getDiscountedPrice()%>
			</td>
			<td>
				<form action="${pageContext.request.contextPath}/consumers/items"
					method="GET">
					<input type="hidden" name="id" value="<%=item.getId()%>">
					<button type="submit" class="btn btn-primary">Get Detail</button>
				</form>
			</td>
			<td>
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#<%="for_sub_" + item.getId()%>">
					Subscribe</button> <!-- Modal -->
				<div class="modal fade" id="<%="for_sub_" + item.getId()%>"
					tabindex="-1"
					aria-labelledby="<%="for_sub_" + item.getId() + "_label"%>"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title"
									id="<%="for_sub" + item.getId() + "_lablel"%>">Modal title</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<form
								action="${pageContext.request.contextPath}/consumers/items/subscribe"
								method="POST">
								<div class="modal-body">
									Subscribe to item
									<%=item.getName()%>
									<input type="hidden" name="item_id" value="<%=item.getId()%>">
									<input type="hidden" name="user_id" value="<%=user.getId()%>">
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary">Confirm</button>
								</div>
							</form>
						</div>
					</div>
				</div> <!--  modal end -->
			</td>
		</tr>
		<%
		}
		%>
		<%
		} else {
		%>
		<div class="d-flex flex-column align-items-center">
			<p class="mt-5">No items found</p>
		</div>
		<%
		}
		%>
	</table>
	<%
	if (user != null) {
	%>
	<form class="mt-3"
		action="${pageContext.request.contextPath}/consumers/items/all">
		<button type="submit" class="btn btn-primary">refresh list</button>
	</form>
	<%
} else {
%>
	<a href="${pageContext.request.contextPath}/pages/login.jsp">
		<button class="btn btn-primary">Please login first</button>
	</a>
	<%
}
%>
</main>
<c:import url="/includes/footer.jsp" />