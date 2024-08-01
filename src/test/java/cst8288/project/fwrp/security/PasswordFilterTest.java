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
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Arrays;

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

	@Test
	void testEmailNotFound() throws SQLException, ServletException, IOException {
		when(request.getParameter("email")).thenReturn("not_found@email.com");
		when(request.getParameter("password")).thenReturn("M0ck!1234");
		jakarta.servlet.http.HttpSession session = mock(jakarta.servlet.http.HttpSession.class);
		when(request.getSession()).thenReturn(session);

		User user = new User();
		user.setEmail("existing@email.com");
		user.setPassword("M0ck!1234");
		UserService userService = mock(UserService.class);

		when(userService.loadUserByEmail("not_found@email.com")).thenReturn(null);
		filter.setUserService(userService);
		filter.doFilter(request, response, chain);

		// Mocking request attributes
		when(request.getAttributeNames()).thenReturn(Collections.enumeration(Arrays.asList("errMsg")));
		when(request.getAttribute("errMsg")).thenReturn("username or password is wrong");
		verify(response).sendRedirect(contains("index.jsp"));
	}

	@Test
	void testPasswordInvalid() throws SQLException, ServletException, IOException {
		when(request.getParameter("email")).thenReturn("existing@email.com");
		when(request.getParameter("password")).thenReturn("wR0ng_Password");
		jakarta.servlet.http.HttpSession session = mock(jakarta.servlet.http.HttpSession.class);
		when(request.getSession()).thenReturn(session);

		User user = new User();
		user.setEmail("existing@email.com");
		user.setPassword("M0ck!1234");
		UserService userService = mock(UserService.class);

		when(userService.loadUserByEmail("not_found@email.com")).thenReturn(user);
		filter.setUserService(userService);
		filter.doFilter(request, response, chain);

		// Mocking request attributes
		when(request.getAttributeNames()).thenReturn(Collections.enumeration(Arrays.asList("errMsg")));
		when(request.getAttribute("errMsg")).thenReturn("username or password is wrong");
		verify(response).sendRedirect(contains("index.jsp"));
	}
}