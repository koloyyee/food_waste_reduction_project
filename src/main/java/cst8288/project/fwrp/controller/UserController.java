package cst8288.project.fwrp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.model.UserType;
import cst8288.project.fwrp.service.UserService;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;
import cst8288.project.fwrp.utils.Validation;
import cst8288.project.fwrp.utils.exception.PasswordInvalidException;

/**
 * Servlet implementation class UserController. <br>
 * Handlers user login, logout, register, delete
 */
@WebServlet(name = "UserController", urlPatterns = "/users/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger logger = LoggerFactory.getLogger();
	private final Validation validation;
	private final UserService userService;

	public UserController() {
		super();
		this.validation = new Validation();
		this.userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info(request.getPathInfo() + " " + request.getRequestURI());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * Create user:<br>
	 * get name, password, email, phone?, type as int.
	 */
	private void register(HttpServletRequest request, HttpServletResponse response) {

		try {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			int userTypeInt = Integer.parseInt(request.getParameter("type"));

			UserType type = UserType.getByCode(userTypeInt);
			if (!validation.checkPassword(password)) {
				// password invalid
				throw new PasswordInvalidException(
						"Password must be 8-99, 1 or more symbols, 1 or more Uppercase and 1 or more lowercase letter, and digit 0-9 ");
			}
			User.Builder builder = new User.Builder(email, password);

			User user = builder.setId(null).setName(name).setPhone(phone).setUserType(type).build();
			userService.register(user);
		} catch (SQLException | RuntimeException e) {
			logger.warn(e.getLocalizedMessage());
		}
	}

}
