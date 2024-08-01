package cst8288.project.fwrp.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

	@Test
	void shouldThrowNullUser() {
		Order order = new Order();
		assertThrows(IllegalArgumentException.class, () -> order.setUser(null));
	}

	@Test
	void shouldThrowNullItem() {
		Order order = new Order();
		assertThrows(IllegalArgumentException.class, () -> order.setItem(null));
	}

	@Test
	void shouldThrowQuantityLessThanZero() {
		assertThrows(IllegalArgumentException.class, () -> new Order().setQuantity(-1));
	}

	@Test
	void  shouldThrowNullTransactionType() {
	assertThrows(IllegalArgumentException.class, () -> new Order().setTransactionType(null));
	}

	@Test
	void shouldThrowTotalAmountLessThanZero() {
		assertThrows(IllegalArgumentException.class, () -> new Order().setTotalAmount(BigDecimal.valueOf(-1)));
	}

	@Test
	void shouldGetTodayAsCreatedAt() {
		Order order = new Order();
		order.setCreatedAt();
		assertEquals(LocalDateTime.now().getYear(), order.getCreatedAt().getYear());
		assertEquals(LocalDateTime.now().getMonth(), order.getCreatedAt().getMonth());
		assertEquals(LocalDateTime.now().getDayOfMonth(), order.getCreatedAt().getDayOfMonth());
	}
}