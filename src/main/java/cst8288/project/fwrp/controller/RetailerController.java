package cst8288.project.fwrp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import cst8288.project.fwrp.model.Item;
import cst8288.project.fwrp.service.ItemService;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;

/**
 * Servlet implementation class RetailerController<br>
 * 
 * <ul>
 * <li>[] View all items: if the item is 1 week before expiry, it should show as
 * surplus.</li>
 * <li>[] View all surplus</li>
 * <li>[] Create, update, and delete items</li>
 * <li>[] Change item status as donated, purchased</li>
 * <li>[] Set if the item is for “donation”</li>
 * <li>[] Set quantities</li>
 * <li>[] Set discount rate</li>
 * </ul>
 */
@WebServlet(name = "RetailerController", urlPatterns = { "/retailers/*" })
public class RetailerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger();

	private ItemService itemService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetailerController() {
		super();
		this.itemService = new ItemService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get item by id
		String path = request.getPathInfo();
		long id = Long.parseLong(request.getParameter("id"));

		try {
			Optional<Item> item = itemService.getItemById(id);
			if (item.isPresent()) {
				request.setAttribute("item", item.get());
//				log.info(item.get().toString());
				request.getRequestDispatcher("/pages/retailer/item.jsp").forward(request, response);
			} else {
				log.warn("Item not found");
				response.sendRedirect("/pages/retailer/index.jsp");
			}

		} catch (Exception e) {
			log.warn(e.getLocalizedMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		log.info("Path: " + path);
		switch (path) {
		case "/items/toggle_donation":
			toggleDontation(request, response);
			break;
		case "/items/toggle_surplus":
			break;
		case "/items/toggle_available":
			break;
		default:
			break;
		}
	}

	private void toggleDontation(HttpServletRequest request, HttpServletResponse response) {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			Optional<Item> item = itemService.getItemById(id);
			if (item.isPresent()) {
				item.get().setDonation(!item.get().isDonation());
				log.info("Item: " + item.get().toString());
				itemService.toggleDonationItem(item.get());
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
