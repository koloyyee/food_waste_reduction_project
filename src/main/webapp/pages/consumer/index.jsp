<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cst8288.project.fwrp.model.User"%>
<%@ page import="cst8288.project.fwrp.model.Item"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/header.jsp" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"/>
<div class="container">
	<h1 class="text-center display-4">Welcome to Consumer Page!</h1>
	<div class="row"></div>
</div>
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
<div class="d-flex flex-column align-items-center">
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
		<form action="${pageContext.request.contextPath}/consumers/items" method="GET">
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
	<p class="text-center"> No items found</p>
	<%
	}
	%>
</table>

<% if (user != null) { %>
<form action="${pageContext.request.contextPath}/pages/consumer/index.jsp">
	<button type="submit" class="btn btn-primary">refresh list</button>
</form>
</div>
<%
} else {
	%>
	<a href="${pageContext.request.contextPath}/index.jsp"> 
		<button class="btn btn-primary">Please login first</button>
	</a>
<%
    }
%>

</body>
</html>