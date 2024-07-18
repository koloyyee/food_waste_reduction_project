package cst8288.project.fwrp.controller;

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
		// TODO Auto-generated method stub

		response.getWriter().append("Served at: ").append(request.getContextPath());
		String path = request.getPathInfo();
		String param = request.getParameter("id");


//		String[] pathArray = path.split("/");
//		System.out.println(pathArray.length);
//		String endpoint = "";
//		String param = "";

//		if (pathArray.length > 1) {
//			endpoint = pathArray[1];
//			param = pathArray[2];
//		}
//
		log.info(path);
		log.info(param);
		try {
			switch (path) {
			case "/items":
				Item it = itemDaoIml.find(Long.parseLong(param)).get();
				log.info(it.toString());
				break;
			default:
				log.info(path);
				break;

			}

		} catch (SQLException e) {
			log.warn(e.getMessage());
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

}
