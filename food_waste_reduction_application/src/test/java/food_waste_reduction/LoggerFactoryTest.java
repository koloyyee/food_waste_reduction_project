package food_waste_reduction;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.Logger;
import utils.LoggerFactory;

class LoggerFactoryTest {

	@Test
	void shouldBeTheSameInstance() {
		Logger l1 = LoggerFactory.getLogger();
		Logger l2 = LoggerFactory.getLogger();
		boolean actual  = l1 == l2;
		assertEquals(true, actual);

	}
	@Test
	void shouldHaveINFO() {
		Logger l1 = LoggerFactory.getLogger();
		String content = l1.info("testing testing");
		boolean actual = content.contains("INFO");
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	void shouldHaveWARNING() {
		Logger l1 = LoggerFactory.getLogger();
		String content = l1.warn("testing testing");
		boolean actual = content.contains("WARNING");
		boolean expected = true;
		assertEquals(expected, actual);
	}
}
