<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="cst8288.project.fwrp.model.Item"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/header.jsp" />

view item detail. Consumer can order item from here. so view, and create
order.

<%
Item item = (Item) request.getAttribute("item");
%>
<%
if (item != null) {
%>
<div class="card">
	<div class="card-header">
		<h3> <%=item.getName()%> </h3>
	</div>
	<div class="card-body">
		<blockquote class="blockquote mb-0">
			<p>Description: <%=item.getDescription()%></p>
			<p>Stock level: <%=item.getQuantity()%></p>
			<p>Original Price: <del>$<%=item.getOriginalPrice()%></del></p>
			<p>Discount: <%=item.getDiscountRate()*100%>% off!</p>
			<p>Price: $<%=item.getPrice()%></p>

			<form
				action="${pageContext.request.contextPath}/consumers/items/order"
				method="POST">
				<input type="hidden" name="id" value="<%=item.getId()%>">
				<button type="submit" class="btn btn-primary">Order it now!</button>
			</form>
		</blockquote>
	</div>
</div>
<%
}
%>

</body>
</html>