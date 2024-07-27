<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="cst8288.project.fwrp.model.User" %>
<%@ page import="cst8288.project.fwrp.model.Item" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/includes/header.jsp"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/pages/consumer/index.jsp" >Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link " aria-current="page" href="${pageContext.request.contextPath}/pages/consumer/index.jsp">Shopping List</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Subscribed To</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Ordered history</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
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
        <th scope="col">Item Name </th>
        <th scope="col">Item Description</th>
        <th scope="col">Item Quantity</th>
        <th scope="col">Item Price</th>
    </tr>
    <%
        for (Item item : items) {
    %>
    <tr>

        <% String surplusMsg = item.isSurplus() ? "(Expiring Soon!)" : "" ; %>
       <% String discountMsg =  item.getDiscountRate() > 0 ? "(Discounted Item!)" : "";  %>

        <td><%=item.getName()%> <span style="color:red" >  <%= surplusMsg %> <%= discountMsg %> </span> </td>
        <td><%=item.getDescription()%>
        </td>
        <td><%=item.getQuantity()%>
        </td>
        <td>$<%=item.getPrice()%>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/consumers/items" method="GET">
                <input type="hidden" name="id" value="<%=item.getId()%>">
                <button type="submit" class="btn btn-primary">Get Detail</button>
            </form>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/consumers/items/subscribe" method="POST">
                <input type="hidden" name="item_id" value="<%=item.getId()%>">
                <input type="hidden" name="user_id" value="<%=user.getId()%>">
                <button type="submit" class="btn btn-primary"> Subscribe </button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
    <%
    } else {
    %>
    <div class="d-flex flex-column align-items-center">
        <p class="mt-5"> No items found</p>
            <%
	}
	%>
</table>
<% if (user != null) { %>
<form class="mt-3" action="${pageContext.request.contextPath}/pages/consumer/index.jsp">
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
<c:import url="/includes/footer.jsp"/>