package cst8288.project.fwrp.utils;

import static org.junit.jupiter.api.Assertions.*;

import cst8288.project.fwrp.service.UserService;
import cst8288.project.fwrp.utils.exception.NoSuchUserException;

class UserServiceTest {
	
//	@Test
	void shouldFailedToLoadUser() {
		UserService service = new UserService();
		
		Exception e = assertThrows(NoSuchUserException.class, () -> {
			service.loadUserByEmail("no_such_email@example.com");
		});
		
		String actual = e.getLocalizedMessage();
		System.out.println(actual);
		String expected = "No user found with email: no_such_email@example.com";
		assertTrue(actual.contains(expected), actual );
	}

}
