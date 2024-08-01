package cst8288.project.fwrp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Differentiated by transaction type.
 * promote immutability.
 */
public class Order {
	private Long id;
	private User user;
	private Item item;
	private int quantity;
	private TransactionType transactionType;
	private BigDecimal totalAmount;
	private LocalDateTime createdAt;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if(user == null ) throw new IllegalArgumentException("User cannot be null");
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		if(item == null ) throw new IllegalArgumentException("Item cannot be null");
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if(quantity < 0 ) throw new IllegalArgumentException("Quantity cannot be negative");
		this.quantity = quantity;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		if(transactionType == null ) throw new IllegalArgumentException("TransactionType cannot be null");
		this.transactionType = transactionType;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		if(totalAmount == null ) throw new IllegalArgumentException("TotalAmount cannot be null");
		if(totalAmount.compareTo(BigDecimal.ZERO) < 0 ) throw new IllegalArgumentException("TotalAmount cannot be negative");
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt() {
		this.createdAt = LocalDateTime.now();
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Order{" + "id=" + id + ", user=" + user + ", item=" + item + ", quantity=" + quantity + ", " +
				"transactionType" + "=" + transactionType + ", totalAmount=" + totalAmount + ", createdAt=" + createdAt + '}';
	}
}

