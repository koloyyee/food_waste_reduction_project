<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="cst8288.project.fwrp.model.Item"%>
<%@ page import="java.time.LocalDate"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/includes/header.jsp" />

<main>
<div class="card w-50 px-2">
        <div class="card-header">
        	            <h3> Create New Item!</h3>
        </div>
	<form class="form-group"
		action="${pageContext.request.contextPath}/retailers/items/create"
		method="POST">
		<div class="mb-3">
			<label for="name" class="form-label"> Product Name</label> <input
				type="email" name="name" class="form-control" id="name" required>
		</div>
		<div class="mb-3">
			<label for="description" class="form-label"> Product
				Description </label> <input type="text" name="description"
				class="form-control" id="description" required>
		</div>
		<div class="mb-3">
			<label for="price" class="form-label"> Price </label> <input
				type="number" name="price" class="form-control" id="price"
				min="0.1"
				>
		</div>
		<div class="mb-3">
			<label for="discount_rate" class="form-label"> Discount Rate</label>
			<input type="number" name="discountRate" class="form-control"
			min="0" max="100" value="0"
				id="discount_rate">
		</div>
		<div class="mb-3">
			<label for="quantity">Quantity:</label> <input type="number"
				name="quantity" class="form-control" min="1" required>
		</div>
		<div class="mb-3">
			<label for="expiry_date">Expiry Date:</label>
			 <input type="date"
				name="expiry_date" class="form-control" required id="expiry_date" >
		</div>
		<div class="mb-3">
			<button type="submit" class="btn btn-primary">Create Item</button>
			<button type="reset" class="btn btn-secondary">Reset</button>
		</div>
	</form>
	</div>
</main>
<script>
	const expiryDate = document.getElementById('expiry_date');
	expiryDate.min = new Date().toISOString().split('T')[0];

</script>
<c:import url="/includes/footer.jsp" />