package cst8288.project.fwrp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Items
 */
public class Item {
	private String name;
	private String description;
	private LocalDate expiryDate;
	private BigDecimal price;
	private double discountRate = 0;
	private boolean isSurplus = false;
	private boolean isDonation = false;
	private int quantity;
	private boolean isAvailable;
	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static void main(String[] args) {
		var i = new Item();

		i.setName("test");
		i.setDescription("test description");
		i.setDiscountRate(0.3);
		i.setPrice(BigDecimal.valueOf(9.99));
		i.setExpiryDate(LocalDate.now().plusWeeks(1));
		i.setSurplus(false);
		System.out.println(i);
		System.out.println(i.checkSurplus());

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * discount rate start at 0% = 0.0, no discount. if 30% off, discount rate will
	 * 1 - 0.3 = 0.7
	 */
	public BigDecimal getPrice() {

		var bdDiscountRate = BigDecimal.valueOf(1 - discountRate);
		return price.multiply(bdDiscountRate);
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	/**
	 * If the expiry date is 1-week away from now, it should return is surplus.
	 */
	public boolean checkSurplus() {
		return ChronoUnit.WEEKS.between(LocalDate.now(), this.expiryDate) <= 1;
	}

	public boolean isSurplus() {
		return isSurplus;
	}

	public void setSurplus(boolean isSurplus) {
		this.isSurplus = isSurplus;
	}

	public boolean isDonation() {
		return isDonation;
	}

	public void setDonation(boolean isDonation) {
		this.isDonation = isDonation;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	@Override
	public String toString() {
		return "Item [name=" + name + ", description=" + description + ", expiryDate=" + expiryDate + ", price=" + price
				+ ", discountRate=" + discountRate +  ", discountedPrice=" + getPrice() + ", isSurplus=" + isSurplus + ", isDonation=" + isDonation
				+ ", quantity=" + quantity + ", isAvailable=" + isAvailable + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
