package cst8288.project.fwrp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;

// for login we will need to do authentication with doFilter 
// https://www.geeksforgeeks.org/servlet-authentication-filter/
/**
 * Servlet implementation class AuthController user login, logout P@$$word123
 */
@WebServlet(name = "AuthController", urlPatterns = "/auth/*")
public class AuthController extends HttpServlet {
	private static Logger logger = LoggerFactory.getLogger();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String route = request.getPathInfo();
		switch (route) {
		case "/login":
			login(request, response);
			break;
		default:
			break;

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) {

		boolean isValid = (boolean) request.getAttribute("isValid");

		if (isValid) {
			User user = (User) request.getAttribute("user");
			logger.info("isValid: " + isValid);
			System.out.println(user);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			response.setContentType("text/html");
//				PrintWriter out = response.getWriter();
//				out.print("welcome to GEEKSFORGEEKS");

		}
	}

}
