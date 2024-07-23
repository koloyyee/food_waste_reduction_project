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
	<tr>
		<td><%=item.getName()%></td>
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

			<form action="${pageContext.request.contextPath}/retailers/items/"
				method="POST">
				<input type="hidden" name="id" value="<%=item.getId()%>">
				<button type="submit" class="btn btn-warning">Mark as
					Surplus</button>
			</form>
		</td>
		<td>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-bs-toggle="modal"
				data-bs-target="#for_donation">Mark as For Donation</button> <!-- Modal -->
			<div class="modal fade" id="for_donation" tabindex="-1"
				aria-labelledby=for_donation_label aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id="for_donation_label">Modal
								title</h1>
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
									<button type="submit" type="button" class="btn btn-info">Update</button>
						</form>
					</div>
				</div>
			</div>
			</div>
			</div>
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