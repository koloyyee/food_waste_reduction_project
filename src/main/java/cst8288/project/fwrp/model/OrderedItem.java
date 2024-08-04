package cst8288.project.fwrp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 * OrderedItem class represents as a historical item in the order.
 * used by Consumer and Charitable Organization.
 */
public record OrderedItem (Long id, String name, String description, BigDecimal price, int quantity, LocalDateTime createdAt) {
	public BigDecimal getPrice() {
        return  price.setScale(2, RoundingMode.HALF_EVEN);
	}
	public String getCreatedAtStr() {
		var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return createdAt.format(formatter);
	}
}

class OrderedItemC {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private int quantity;
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public BigDecimal getPrice() {
        return  price.setScale(2, RoundingMode.HALF_EVEN);
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public String getCreatedAtStr() {
		var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return createdAt.format(formatter);
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + ", createdAt=" + createdAt + "]";
	}

	
	
}
