package fwrp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cst8288.project.fwrp.utils.Validation;


class ValidationTest {

	@Test
	void shouldReturnTrue() {
		var v = new Validation();
		boolean actual = v.checkPassword("P@$$word123");
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	void pwLessThan8shouldReturnFalse() {
		var v = new Validation();
		boolean actual = v.checkPassword("P@$$");
		boolean expected = false;
		assertEquals(expected, actual);
	}

	@Test
	void pwNoSymboleshouldReturnFalse() {
		var v = new Validation();
		boolean actual = v.checkPassword("P11111111111111");
		boolean expected = false;
		assertEquals(expected, actual);
	}
	@Test
	void pwWithSpacehouldReturnFalse() {
		var v = new Validation();
		boolean actual = v.checkPassword("P           ");
		boolean expected = false;
		assertEquals(expected, actual);
	}
	@Test
	void pwShouldReturnFalse() {
		var v = new Validation();
		boolean actual = v.checkPassword("Password");
		boolean expected = false;
		assertEquals(expected, actual);
	}
}
