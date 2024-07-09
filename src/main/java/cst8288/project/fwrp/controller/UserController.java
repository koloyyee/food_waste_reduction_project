package cst8288.project.fwrp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.model.UserType;
import cst8288.project.fwrp.utils.Validation;

/**
 * Servlet implementation class UserController. <br>
 * Handlers user login, logout, register, delete
 */
@WebServlet(name = "UserControoller", urlPatterns = "/users/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Validation validation = new Validation();

	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	private void register(HttpServletRequest request) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int userTypeInt = Integer.parseInt(request.getParameter("type"));

		UserType type = UserType.getByCode(userTypeInt);
		if (validation.checkPassword(password)) {
			User.Builder user = new User.Builder(email, password);
			user.setId(null).setName(name).setPhone(phone).setUserType(type);
		}

	}

}
