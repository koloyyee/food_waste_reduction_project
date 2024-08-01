package fwrp.controller;




import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;


import cst8288.project.fwrp.dao.UserDaoImpl;
import cst8288.project.fwrp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import cst8288.project.fwrp.controller.AuthController;
import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.utils.Validation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mockito.MockitoAnnotations;

class AuthControllerTest {


	@Mock
	private UserService userService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		userService = new UserService();
	}
	
	@Test
	void testDoGet() throws ServletException, IOException {
	
		
		HttpServletRequest request = Mockito.mock( HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock( HttpServletResponse.class);

		when(request.getParameter("email")).thenReturn("c@fwrp.com");
        when(request.getParameter("password")).thenReturn("P@$$word123");
	
        	
        String email = request.getParameter("email");
        String password = request.getParameter("password");
	
        User user = new User();
      	user.setEmail("c@fwrp.com");
      	user.setPassword("P@$$word123");
       	
      	assertEquals(user.getEmail(), email);
      	assertEquals(user.getPassword(), password);
        
       AuthController authController = new AuthController();
       authController.doGet(request, response);
       Mockito.verify(response).sendRedirect("/index.jsp");

	}

	@Test
	public void testFindUserByEmail() throws SQLException {
		User user = new User();
		user.setEmail("c@fwrp.com");
		when(userService.loadUserByEmail("c@fwrp.com")).thenReturn(user);
	}

}
