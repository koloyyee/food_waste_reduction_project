<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/includes/header.jsp" />
<main>

	ORDER PLACED! click to go back all items.
	<div>
		<a href="${pageContext.request.contextPath}/pages/charity/index.jsp">
			<button class="btn btn-primary">Back</button>
		</a>

	</div>
</main>
<c:import url="/includes/footer.jsp" />