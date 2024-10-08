<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="cst8288.project.fwrp.model.User"%>
<%@ page import="cst8288.project.fwrp.model.Item"%>
<%@ page import="java.util.List"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/charity_nav.jsp" />
<main>
	<h1>Welcome to Charitable Organization Page!</h1>
	<%
	User user = (User) request.getSession().getAttribute("user");
	%>
	<p>Welcome back! ${user.getName()}</p>


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
			<td>$<%=item.getDiscountedPrice()%></td>
			<td>
				<form
					action="${pageContext.request.contextPath}/charitable_orgs/items"
					method="GET">
					<input type="hidden" name="id" value="<%=item.getId()%>">
					<button type="submit" class="btn btn-primary">Get Detail</button>
				</form>
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
		action="${pageContext.request.contextPath}/pages/charity/index.jsp">
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
</body>
</html>