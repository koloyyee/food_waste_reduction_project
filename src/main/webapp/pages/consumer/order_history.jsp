<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cst8288.project.fwrp.model.User"%>
<%@ page import="cst8288.project.fwrp.model.OrderedItem"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/consumer_nav.jsp" />

<%
User user = (User) request.getSession().getAttribute("user");
%>
<%
List<OrderedItem> items = (List<OrderedItem>) request.getAttribute("items");
%>

<%
if (items != null && items.size() > 0) {
%>

<table class="table">
	<tr>
		<th scope="col">Item Name</th>
		<th scope="col">Item Description</th>
		<th scope="col">Quantity</th>
		<th scope="col">Price</th>
		<th scope="col">Date Purchased</th>
	</tr>
	<%
	for (OrderedItem item : items) {
	%>
	<tr>

		<td><%=item.name()%></td>
		<td><%=item.description()%></td>
		<td><%=item.quantity()%></td>
		<td>$<%=item.getPrice()%>
		<td><%= item.getCreatedAtStr() %>
		</td>
		<%--         
		<td>
            <form action="${pageContext.request.contextPath}/consumers/items" method="GET">
                <input type="hidden" name="id" value="<%=item.getId()%>">
                <button type="submit" class="btn btn-primary">Get Detail</button>
            </form>
        </td>
 --%>
		<td>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-bs-toggle="modal"
				data-bs-target="#<%="for_sub_" + item.id()%>">Subscribe</button>
			<!-- Modal -->
			<div class="modal fade" id="<%="for_sub_" + item.id()%>"
				tabindex="-1"
				aria-labelledby="<%="for_sub_" + item.id() + "_label"%>"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title"
								id="<%="for_sub" + item.id() + "_lablel"%>">Modal title</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<form
							action="${pageContext.request.contextPath}/consumers/items/subscribe"
							method="POST">
							<div class="modal-body">
								Subscribe to item
								<%=item.name()%>
								<input type="hidden" name="item_id" value="<%=item.id()%>">
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
		<td>
	</tr>
	<%
	}
	%>
	<%
	}
	%>
</table>
<div class="back-btn">
	<button class="btn btn-primary" type="button" onclick="history.back()">Back</button>
</div>
<c:import url="/includes/footer.jsp" />