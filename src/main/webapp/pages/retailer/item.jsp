<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="cst8288.project.fwrp.model.Item"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/retailer_nav.jsp" />
<!--
view item detail. Consumer can order item from here. so view, and create
order.
-->
<%
Item item = (Item) request.getAttribute("item");
%>
<%
if (item != null) {
%>
<main>
	<div class="card w-50 px-2">
		<div class="card-header">
			<h3>
				<%=item.getName()%>
			</h3>
		</div>
		<div class="card-body">
			<blockquote class="blockquote mb-0">
				<form class="form-group"
					action="${pageContext.request.contextPath}/retailers/items/update"
					method="POST">
					<input type="hidden" name="id" value="<%=item.getId()%>">
					<div class="mb-3">
						<label for="name" class="form-label"> Product Name</label> <input
							type="text" name="name" class="form-control" id="name"
							value="${item.getName() }">
					</div>
					<div class="mb-3">
						<label for="description" class="form-label"> Product
							Description </label> <input type="text" name="description"
							class="form-control" id="description"
							value="${item.getDescription() }">
					</div>
					<div class="mb-3">
						<label for="price" class="form-label"> Price </label> <input
							type="number" name="price" class="form-control" id="price"
							value="${item.getOriginalPrice() }">
					</div>
					<div class="mb-3">
						<label for="discount_rate" class="form-label"> Discount
							Rate</label> <input type="number" name="discountRate"
							class="form-control" id="discount_rate"
							value="${item.getDiscountRate() * 100 }">
					</div>
					<div class="mb-3">
						<label for="quantity">Quantity:</label> <input type="number"
							name="quantity" class="form-control" min="1"
							value="<%=item.getQuantity()%>" required>
					</div>

					<div class="mb-3">
						<p>
							Is Surplus?
							<%=item.isSurplus() ? "✅" : "❌"%>
						</p>
					</div>
					<div class=" mb-3">
						<p>
							Is Donation?
							<%=item.isDonation() ? "✅" : "❌"%>
						</p>
					</div>
					<div class="mb-3">
						<p>
							Is Available?
							<%=item.isAvailable() ? "✅" : "❌"%>
						</p>
					</div>
					<input type="hidden" name="price" value="<%=item.getDiscountedPrice()%>">
					<button type="submit" class="btn btn-primary">Update
						Product</button>

					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target=<%="#" + "for_remove_" + item.getName()%>>Remove
						Item</button>
					<!-- Modal -->
					<div class="modal fade" id=<%="for_remove_" + item.getName()%>
						tabindex="-1"
						aria-labelledby=<%="for_donation_" + item.getName() + "label"%>
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5"
										id=<%="for_remove_" + item.getName() + "label"%>>Remove
										Item</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<form
									action="${pageContext.request.contextPath}/retailers/items/delete"
									method="POST">
									<div class="modal-body">
										<p><%=item.getName()%>
											Remove from Item List?
										</p>
										<input type="hidden" name="id" value="<%=item.getId()%>">
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Close</button>
											<button type="submit" type="button" class="btn btn-danger">Remove</button>

										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- Button trigger modal -->
				</form>
			</blockquote>
		</div>
		<%
		}
		%>

	</div>
	<%
	String errMsg = (String) request.getAttribute("errMsg");
	%>
	<%
	if (errMsg != null) {
	%>
	<div class="alert alert-danger" role="alert">
		<%=errMsg%>
	</div>

	<%
	}
	%>

	<div class="back-btn">
		<a href="${pageContext.request.contextPath}/pages/retailer/index.jsp">
			<button class="btn btn-primary">Back</button>
		</a>
	</div>
	
</main>
<c:import url="/includes/footer.jsp" />