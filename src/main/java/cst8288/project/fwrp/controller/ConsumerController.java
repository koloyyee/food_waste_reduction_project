package cst8288.project.fwrp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import cst8288.project.fwrp.dao.ItemDaoImpl;
import cst8288.project.fwrp.model.Item;
import cst8288.project.fwrp.utils.Logger;

/**
 * Servlet implementation class ConsumerController
 */
@WebServlet(name = "ConsumerController", urlPatterns = { "/consumers/items/*", "/consumers/users/*",
		"/consumers/orders/*" })
public class ConsumerController extends HttpServlet {
	private Logger log = Logger.getLogger();
	private static final long serialVersionUID = 1L;
	private ItemDaoImpl itemDaoIml;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsumerController() {
		super();
		// TODO Auto-generated constructor stub
		this.itemDaoIml = new ItemDaoImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();

		switch (path) {
		case "/items":
			handleGetItem(request, response);
			break;
		default:
			log.info(path);
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
//		doGet(request, response);
		log.info("POST");
	}

	private void handleGetItem(HttpServletRequest request, HttpServletResponse response) {
		String param = request.getParameter("id");
		try {
			Item item = itemDaoIml.find(Long.parseLong(param)).get();
			log.info(item.toString());
			request.setAttribute("item", item);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/pages/consumer/item.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException | SQLException | ServletException | IOException e) {
			log.warn(e.getLocalizedMessage());
		}
	}
}
