package cst8288.project.fwrp.security;

import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PasswordFilterTest {

	private PasswordFilter filter;
	@Mock private HttpServletRequest request;
	@Mock private HttpServletResponse response;
	@Mock private FilterChain chain;
	@Mock private FilterConfig config;

	@BeforeEach void setUp() throws ServletException {
		MockitoAnnotations.openMocks(this);
		filter = new PasswordFilter();
		filter.init(config);
	}

	@Test
	void testDoFilterValidUser() throws SQLException, ServletException, IOException {
		when(request.getParameter("email")).thenReturn("test@fwrp.com");
		when(request.getParameter("password")).thenReturn("M0ck!1234");
		when(request.getSession()).thenReturn(Mockito.mock(jakarta.servlet.http.HttpSession.class));

		User user = new User();
		user.setEmail("test@fwrp.com");
		user.setPassword("M0ck!1234");
		UserService userService = mock(UserService.class);
		when(userService.loadUserByEmail("test@fwrp.com")).thenReturn(user);
		filter.setUserService(userService);

		filter.doFilter(request, response, chain);
		verify(chain).doFilter(request, response);
	}
}