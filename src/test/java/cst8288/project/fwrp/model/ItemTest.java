package cst8288.project.fwrp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import cst8288.project.fwrp.utils.exception.IllegalDiscountRate;
import org.junit.jupiter.api.Test;

class ItemTest {

	@Test
	void shouldGetCorrectDiscountedPrice() {
		Item item = new Item();
        item.setPrice(BigDecimal.valueOf(100));
        item.setDiscountRate( 0.10);
        BigDecimal expected = BigDecimal.valueOf(90).setScale(2, RoundingMode.HALF_EVEN) ;
        BigDecimal actual = item.getDiscountedPrice();
        assertEquals(expected ,actual);
	}
	
	@Test
	void shouldGetCorrectDiscountRate() {
		Item item = new Item();
				item.setDiscountRate( 0.10);
				double expected = 0.10;
				double actual = item.getDiscountRate();
				assertEquals(expected ,actual);
	}
	@Test void shouldRejectDiscountRateLessThanZero() {
		Item item = new Item();
		assertThrows(IllegalDiscountRate.class, ()-> item.setDiscountRate(50));

	}
}
