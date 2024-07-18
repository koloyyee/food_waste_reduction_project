package cst8288.project.fwrp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import cst8288.project.fwrp.dao.ItemDaoImpl;
import cst8288.project.fwrp.model.Item;
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
	private static Logger log = LoggerFactory.getLogger();
	private static final long serialVersionUID = 1L;
	private ItemDaoImpl itemDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthController() {
		super();
		this.itemDao = new ItemDaoImpl();
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
			logout(request, response);
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
			try {
			User user = (User) request.getAttribute("user");
			request.getSession().setAttribute("user", user);

			String userTypeJsp = request.getContextPath() + "/pages";
			
			
			switch (user.getType()) {
			case Retailer:
				userTypeJsp += "/retailer/index.jsp";
				List<Item> items = itemDao.findAll();
				log.info(items);
				request.getSession().setAttribute("items", items);
				break;
			case CharitableOrg:
				userTypeJsp += "/charity/index.jsp";
				List<Item> donations = itemDao.findDonations();
				request.getSession().setAttribute("items", donations);
				log.info(donations);
				break;
			default:
				userTypeJsp += "/consumer/index.jsp";
				List<Item> surplus = itemDao.findSurplus();
				log.info(surplus);
				request.getSession().setAttribute("items", surplus);
				break;
			}
			
				response.sendRedirect(userTypeJsp);
			} catch (IOException | SQLException e) {
				log.warn(e.getLocalizedMessage());
			}

		} else {
			response.setContentType("text/html");
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {
				log.warn(e.getLocalizedMessage());
			}

		}

	}

	protected void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		try {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} catch (IOException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

}
