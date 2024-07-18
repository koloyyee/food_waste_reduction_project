<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cst8288.project.fwrp.model.User"%>
<%@ page import="cst8288.project.fwrp.model.Item"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/header.jsp" />
	<h1>Welcome to Consumer Page!</h1>
	<% User user = (User) request.getSession().getAttribute("user");	%>
	<% List<Item> items = (List<Item>) request.getSession().getAttribute("items");	%>
	
	<% if( items != null && items.size() > 0) { %>)
	Hello
	<% } else { %>
	    <p> No items found </p>
	    <% } %>

	<form action="${pageContext.request.contextPath}/consumers/hello">
		<input type="text" name="test" > 
		<button type="submit"> submit </button>
	</form>
</body>
</html>