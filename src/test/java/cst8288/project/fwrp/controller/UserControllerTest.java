package cst8288.project.fwrp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cst8288.project.fwrp.dao.UserDaoImpl;
import cst8288.project.fwrp.model.CommMethodType;
import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.model.UserType;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;
import cst8288.project.fwrp.utils.exception.PasswordInvalidException;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

class UserControllerTest {
	private Logger log = LoggerFactory.getLogger();

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private UserController userController;

	@Mock
	private UserDaoImpl userDao;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		userController = new UserController();
		userDao = new UserDaoImpl();
	}

//	@Test
	void testHandleRegister() throws ServletException, java.io.IOException, SQLException {
		User mockUser = Mockito.mock(User.class);

		// Define the behavior of the mock objects
		when(request.getPathInfo()).thenReturn("/register");
		when(request.getParameter("name")).thenReturn("Test User");
		when(request.getParameter("password")).thenReturn("TestPassword123!");
		when(request.getParameter("email")).thenReturn("testuser@example.com");
		when(request.getParameter("phone")).thenReturn("1234567890");
		when(request.getParameter("commMethod")).thenReturn(CommMethodType.Email.name());
		when(request.getParameter("location")).thenReturn("Test Location");

		when(mockUser.getName()).thenReturn("Test User");
		when(mockUser.getPassword()).thenReturn("TestPassword123!");
		when(mockUser.getEmail()).thenReturn("testuser@example.com");
		when(mockUser.getPhone()).thenReturn("1234567890");
		when(mockUser.getType()).thenReturn(UserType.Consumer);
		when(mockUser.getCommMethod()).thenReturn(CommMethodType.Email);
		when(mockUser.getLocation()).thenReturn("Test Location");

		when(userDao.save(mockUser)).thenReturn(mockUser);
		log.info("User registered: " + mockUser);
		when(request.getSession()).thenReturn(Mockito.mock(HttpSession.class));

		// Call the method to test
		userController.doGet(request, response);

		// Verify that the getSession method was called
		verify(request).getSession();
	}

//@Test
	void testHandleRegisterInvalidPassword() throws ServletException, IOException {
		when(request.getPathInfo()).thenReturn("/register");
		when(request.getParameter("name")).thenReturn("Test User");
		when(request.getParameter("password")).thenReturn("invalid"); // Invalid password
		when(request.getParameter("email")).thenReturn("testuser2@example.com");
		when(request.getParameter("phone")).thenReturn("1234567890");
		when(request.getParameter("commMethod")).thenReturn(CommMethodType.Email.name());
		when(request.getParameter("location")).thenReturn("Test Location");

		assertThrows(PasswordInvalidException.class, () -> {
			userController.doGet(request, response);
		});
	}

}
