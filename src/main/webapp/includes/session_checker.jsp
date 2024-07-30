<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="cst8288.project.fwrp.model.User"%>
    
		<%
		User user = (User) request.getSession().getAttribute("user");
		%>
		<%
		if (user == null) {
		%>
		<%
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		%>
		<%
		} else if (!request.getServletPath().contains(user.getType().name().toLowerCase())) {
		%>

		<%
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		%>
		<%
		}
		%>