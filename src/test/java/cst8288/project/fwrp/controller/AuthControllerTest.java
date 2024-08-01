package cst8288.project.fwrp.controller;




import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;


import cst8288.project.fwrp.model.UserType;
import cst8288.project.fwrp.service.UserService;
import jakarta.servlet.http.HttpServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import cst8288.project.fwrp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

class AuthControllerTest {
	@Spy private AuthController servlet;
	@Mock private HttpServletRequest request;
	@Mock private HttpServletResponse response;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void shouldRedirectToIndex() throws Exception {
		when(request.getPathInfo()).thenReturn("/auth/login");
		when(request.getParameter("email"))	.thenReturn("mock@fwrp.com");
		when(request.getParameter("password"))	.thenReturn("M0cK!1234");

		User mockUser = new User();
		mockUser.setEmail("mock@fwrp.com");
		mockUser.setPassword("M0cK!1234");
		mockUser.setType(UserType.Consumer);
		when(request.getSession()).thenReturn(Mockito.mock(jakarta.servlet.http.HttpSession.class));
		when(request.getAttribute("user")).thenReturn(mockUser);
		servlet.doGet(request, response);
		verify(response).sendRedirect(contains("index.jsp"));
	}

	@Test
	public void shouldRedirectInvalidUser() throws Exception {
		when(request.getPathInfo()).thenReturn("/auth/login");
		when(request.getParameter("email")).thenReturn("invalid@email.com");
		when(request.getParameter("password")).thenReturn("invalidPassword");

		User mockUser = new User();
		mockUser.setEmail("valid@email.com");
		mockUser.setPassword("validPassword");

		when(request.getSession()).thenReturn(Mockito.mock(jakarta.servlet.http.HttpSession.class));
		when(request.getAttribute("user")).thenReturn(mockUser);
		servlet.doGet(request, response);
		verify(response).sendRedirect(contains("index.jsp"));
	}
}
