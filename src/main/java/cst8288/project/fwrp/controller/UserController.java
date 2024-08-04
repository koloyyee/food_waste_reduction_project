package cst8288.project.fwrp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;

import cst8288.project.fwrp.model.CommMethodType;
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		switch (path) {
		case "/update":
			handleUpdate(request, response);
			break;
		case "/register":
			handleRegister(request, response);
			break;
		default:
			break;
		}
	}

	
	/**
	 * Update user profile
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 * */
	private void handleUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String location = request.getParameter("location");
			CommMethodType commMethod = CommMethodType.valueOf(request.getParameter("commMethod"));

			User user = new User();
			user.setId(id);
			user.setName(name);
			user.setEmail(email);
			user.setPhone(phone);
			user.setLocation(location);
			user.setCommMethod(commMethod);

			int updatedRow = userService.update(user);

			// get the user from database no matter update success or not
			// if success we will get the latest version, if not it will be the version existed.
			request.getSession().setAttribute("user", userService.loadUserByEmail(email));
			
			if (updatedRow == 1) {
				request.setAttribute("msg", "Profile updated successfully");
			} else {
				request.setAttribute("msg", "Profile updated failed! Please try again.");
			}
			request.getRequestDispatcher("/pages/edit_profile.jsp").forward(request, response);
		} catch (SQLException | IOException | ServletException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	/**
	 * Create user:<br>
	 * get name, password, email, phone?, type as int.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void handleRegister(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		try {

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			UserType type = UserType.valueOf(request.getParameter("type"));
			CommMethodType commMethod = CommMethodType.valueOf(request.getParameter("commMethod"));
			String location = request.getParameter("location");

			if (!validation.checkPassword(password)) {
				// password invalid

				String msg = "Password must be 8-99, 1 or more symbols, 1 or more Uppercase and 1 or more lowercase letter, and digit 0-9 ";
				throw new PasswordInvalidException(msg);
			}
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setEmail(email.toLowerCase());
			user.setPhone(phone);
			user.setType(type);
			user.setCommMethod(commMethod);
			user.setLocation(location);

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
			response.setContentType("text/html");
			request.setAttribute("errMsg", e.getLocalizedMessage());
			response.sendRedirect(request.getContextPath() + "/pages/register.jsp");
		}
	}

}
