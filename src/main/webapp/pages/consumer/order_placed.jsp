<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/includes/header.jsp" />

ORDER PLACED! click to go back all items.
<a href="${pageContext.request.contextPath}/pages/consumer/index.jsp">
	<button class="btn btn-primary"> Back</button>
</a>

<c:import url="/includes/footer.jsp" />