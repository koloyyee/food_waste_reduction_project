
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.Logger;
import utils.LoggerFactory;

class LoggerFactoryTest {

	@Test
	void test() {
		Logger l1 = LoggerFactory.getLogger();
		Logger l2 = LoggerFactory.getLogger();
		boolean actual  = l1 == l2;
		assertEquals(true, actual);

	}

}
