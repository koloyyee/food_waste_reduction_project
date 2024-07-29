
<!-- Modal -->
<div class="modal fade" id="create_modal" tabindex="-1"
	aria-labelledby="create_modal_label" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="create_modal_label">Modal
					title</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form action="${pageContext.request.contextPath}/retailers/items/create" method="POST">
					<input type="hidden" name="id">
					<div class="mb-3">
						<label for="name" class="form-label"> Product Name</label> <input
							type="text" name="name" class="form-control" id="name">
					</div>
					<div class="mb-3">
						<label for="description" class="form-label"> Product
							Description </label> <input type="text" name="description"
							class="form-control" id="description">
					</div>
					<div class="mb-3">
						<label for="price" class="form-label"> Price($) </label> <input
							type="number" name="price" class="form-control" id="price" min="0" step=".01">
					</div>
					<div class="mb-3">
						<label for="discount_rate" class="form-label"> Discount
							Rate (%)</label> <input type="number" name="discountRate"
							class="form-control" id="discount_rate" min="0" max="100">
					</div>
					<div class="mb-3">
						<label for="quantity">Quantity:</label> <input type="number"
							name="quantity" class="form-control" min="1" required>
					</div>

					<div class="mb-3">
						<label for="expiry_date">Expiry Date:</label> <input type="date"
							name="expiryDate" class="form-control" min="1" required>
					</div>

					<div class="mb-3">
						<label for="is_surplus" class="form-check-label">Is it a
							Surplus Item?:</label> <input type="checkbox" name="isSurplus"
							class="form-check-input" >
					</div>

					<div class="mb-3">
						<label for="is_donation" class="form-check-label">Is it
							for Donation?:</label> <input type="checkbox" name="isDonation"
							class="form-check-input" >
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Create</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
