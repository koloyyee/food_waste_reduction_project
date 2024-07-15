package cst8288.project.fwrp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.model.UserType;
import cst8288.project.fwrp.service.UserService;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;
import cst8288.project.fwrp.utils.Validation;
import cst8288.project.fwrp.utils.exception.PasswordInvalidException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserController. <br>
 * Handlers register, delete
 */
@WebServlet(name = "UserController", urlPatterns = { "/users/*" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger log = LoggerFactory.getLogger();
	private final Validation validation;
	private final UserService userService;

	public UserController() {
		super();
		this.validation = new Validation();
		this.userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String route = request.getPathInfo();
		switch (route) {
		case "/register":
			register(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * Create user:<br>
	 * get name, password, email, phone?, type as int.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void register(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		try {

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			UserType type = UserType.valueOf(request.getParameter("type"));

			if (!validation.checkPassword(password)) {
				// password invalid

				String msg = "Password must be 8-99, 1 or more symbols, 1 or more Uppercase and 1 or more lowercase letter, and digit 0-9 ";
				throw new PasswordInvalidException(msg);
			}
			User.Builder builder = new User.Builder(email, password);

			User user = builder.setId(null).setName(name).setPhone(phone).setUserType(type).build();
			userService.register(user);

//			RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/login");
//			request.setAttribute("user", user);
//			request.setAttribute("email", user.getEmail());
//			request.setAttribute("password", user.getPassword());
//			dispatcher.forward(request, response);
////			response.sendRedirect(request.getContextPath() +"/index.jsp");
			request.getSession().setAttribute("user", user);

			String userTypeJsp = request.getContextPath() + "/pages";
			switch (user.getType()) {
			case Retailer:
				userTypeJsp += "/retailer/index.jsp";
				break;
			case CharitableOrg:
				userTypeJsp += "/charity/index.jsp";
				break;
			default:
				userTypeJsp += "/consumer/index.jsp";
				break;
			}

			try {
				response.sendRedirect(userTypeJsp);
			} catch (IOException e) {
				log.warn(e.getLocalizedMessage());
			}

		} catch (SQLException | RuntimeException e) {
			log.warn(e.getLocalizedMessage());
			// return failed snippet?
//			PrintWriter writer = response.getWriter();
//			writer.print(e.getLocalizedMessage());
//
			response.setContentType("text/html");
			request.setAttribute("errMsg", e.getLocalizedMessage());
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp");
		}
	}

}
