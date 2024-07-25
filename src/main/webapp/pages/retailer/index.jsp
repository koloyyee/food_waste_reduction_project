<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="cst8288.project.fwrp.model.User"%>
<%@ page import="cst8288.project.fwrp.model.Item"%>
<%@ page import="java.util.List"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/includes/header.jsp" />
<%
User user = (User) request.getSession().getAttribute("user");
%>
<%
List<Item> items = (List<Item>) request.getSession().getAttribute("items");
%>

<%
if (items != null && items.size() > 0) {
%>
<!-- create table  -->
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
	<% String surplusMsg = (boolean) item.checkSurplus() ? "(Expiring less than 1 week!)" : ""; %>
	<tr>
		<td><%=item.getName() %> <span style="color:red" >  <%= surplusMsg %> </span></td>
		<td><%=item.getDescription()%></td>
		<td><%=item.getQuantity()%></td>
		<td>$<%=item.getPrice()%></td>
		<td>
			<form action="${pageContext.request.contextPath}/retailers/items"
				method="GET">
				<input type="hidden" name="id" value="<%=item.getId()%>">
				<button type="submit" class="btn btn-primary">Get Detail</button>
			</form>
		</td>
		<td>
			<!-- Button trigger modal --> 

			<button type="button" class="btn btn-warning" data-bs-toggle="modal"
				data-bs-target=<%="#" +  "for_surplus_" + item.getName()%>>Surplus Status</button> <!-- Modal -->
			<div class="modal fade" id=<%= "for_surplus_" + item.getName()%> tabindex="-1" aria-labelledby=for_surplus_label aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id=<%= "for_surplus_" + item.getName()+ "label"%>>>Modal title</h1>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<form
							action="${pageContext.request.contextPath}/retailers/items/toggle_surplus"
							method="POST">
							<div class="modal-body">
								<p>
									<%=item.getName()%>

									<%
									if (item.checkSurplus()) {
									%>
									<span style="color: red">will expire in a week.</span>
									<%
									}
									%>

									<%
									if (!item.isSurplus()) {
									%>
								
								<p>Do you watch set to surplus?</p>
								<!-- create input for update the discount rate -->
								<label for="discountRate">Discount Rate:</label> <input
									type="number" name="discountRate" min="0" max="100" step="1"
									required>
								<%
								} else if (!item.isSurplus()) {
								%>
								is not surplus , do you want to change it? <label
									for="discountRate">Discount Rate:</label> <input type="number"
									name="discountRate" min="0" max="100" step="1" required>
								<%
								} else {
								%>
								is surplus , do you want to change it?
								<%
								}
								%>
								</p>
								<input type="hidden" name="id" value="<%=item.getId()%>">
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<button type="submit" type="button" class="btn btn-danger">Update</button>

								</div>
							</div>
						</form>
					</div>
				</div>
			</div> <!-- Button trigger modal -->
		</td>
		<td>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-info" data-bs-toggle="modal"
				data-bs-target=<%="#" +  "for_donation_" + item.getName()%>>Donation Status</button> <!-- Modal -->
			<div class="modal fade" id=<%= "for_donation_" + item.getName()%> tabindex="-1"
				aria-labelledby=<%= "for_donation_" + item.getName() + "label"%> aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id=<%="for_donation_" + item.getName() + "label"%>>Modal title</h1>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<form
							action="${pageContext.request.contextPath}/retailers/items/toggle_donation"
							method="POST">
							<div class="modal-body">
								<p><%=item.getName()%>
									${item.isDonation() ? 'is for donation' : 'is not for donation yet. '},
									Do you want to change it?
								</p>
								<input type="hidden" name="id" value="<%=item.getId()%>">
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<button type="submit" type="button" class="btn btn-danger">Update</button>

								</div>
							</div>
						</form>
					</div>
				</div>
			</div> <!-- Button trigger modal -->
		</td>
	</tr>
	<%
	}
	%>
	<%
	} else {
	%>
	<p>No items found</p>
	<%
	}
	%>
</table>

<%
if (user != null) {
%>
<form
	action="${pageContext.request.contextPath}/pages/retailer/index.jsp">
	<button type="submit" class="btn btn-primary">refresh list</button>
</form>
<%
} else {
%>
<a href="${pageContext.request.contextPath}/index.jsp">
	<button class="btn btn-primary">Please login first</button>
</a>
<%
}
%>


<c:import url="/includes/footer.jsp" />