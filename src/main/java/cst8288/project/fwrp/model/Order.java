package cst8288.project.fwrp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
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

