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
		<th scope="col">Date Claimed</th>
	</tr>
	<%
	for (OrderedItem item : items) {
	%>
	<tr>

		<td><%=item.name()%></td>
		<td><%=item.description()%></td>
		<td><%=item.quantity()%></td>
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

				<form
					action="${pageContext.request.contextPath}/charitable_orgs/items"
					method="GET">
					<input type="hidden" name="id" value="<%=item.id()%>">
					<button type="submit" class="btn btn-primary">Get Detail</button>
				</form>
<%-- 						<form
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
 --%>				
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