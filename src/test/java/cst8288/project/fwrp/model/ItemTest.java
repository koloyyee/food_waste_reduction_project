package cst8288.project.fwrp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

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
	@Test void shouldRejectDiscountRateGreaterThanOne() {
		Item item = new Item();
		assertThrows(IllegalDiscountRate.class, ()-> item.setDiscountRate(50));
	}

	@Test void shouldRejectDiscountRateLessThanZero() {
		Item item = new Item();
		assertThrows(IllegalDiscountRate.class, ()-> item.setDiscountRate(-1));
	}

	@Test void shouldGetCorrectOriginalPrice() {
		Item item = new Item();
		item.setPrice(BigDecimal.valueOf(100));
		item.setDiscountRate(0.5);
		BigDecimal expected = BigDecimal.valueOf(100);
		BigDecimal actual = item.getOriginalPrice();
		assertEquals(expected, actual);
	}
	@Test void shouldBeSurplus() {
		Item item = new Item();
		item.setAvailable(true);
		item.setExpiryDate(LocalDate.of(2024, 8, 2));
		boolean expected = true;
		boolean actual = item.checkSurplus();
		assertEquals(expected, actual);
	}
}
