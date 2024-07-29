package cst8288.project.fwrp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Enumeration;
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

/*************************************************************************************************************
 * File Name: RetailerController.java Description: This file contains the
 * RetailerController class. This class is used to handle retailer operations
 * such as viewing all items, viewing all surplus, creating, updating, and
 * deleting items, changing item status, setting if the item is for “donation”,
 * setting quantities, and setting discount rate.
 * <hr>
 * An abstract layer between DAO and view. <br>
 *
 * Consumer allowed methods: None <br>
 * Charitable Organization allowed methods: None <br>
 * Retailer allowed methods: doGet, doPost, toggleDontation, toggleSurplus,
 * toggleAvailable, rerenderItemList <br>
 *
 ************************************************************************************************************/
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

		String path = request.getPathInfo();
		switch (path) {
		case "/items":
			handleGetItem(request, response);
			break;
		case "/items/all":
			handleGetAllItems(request, response);
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
		String path = request.getPathInfo();
		log.info("Path: " + path);
		switch (path) {
		case "/items/toggle_donation":
			toggleDontation(request, response);
			break;
		case "/items/toggle_surplus":
			toggleSurplus(request, response);
			break;
		case "/items/toggle_available":
			toggleAvailable(request, response);
			break;
		case "/items/delete":
			handleDelete(request, response);
			break;
		case "/items/create":
			handleCreate(request, response);
			break;
		case "/items/update":
			handleUpdate(request, response);
			break;
		default:
			break;
		}
	}

	// handle GET
	private void handleGetAllItems(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().setAttribute("items", itemService.getItems());
			request.getRequestDispatcher("/pages/retailer/index.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			log.warn(e.getLocalizedMessage());
		}

	}

	private void handleGetItem(HttpServletRequest request, HttpServletResponse response) {
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

	// handle POST
	private void toggleDontation(HttpServletRequest request, HttpServletResponse response) {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			Optional<Item> item = itemService.getItemById(id);
			if (item.isPresent()) {
				item.get().setDonation(!item.get().isDonation());
				int updatedRow = itemService.toggleDonationItem(item.get());
				if (updatedRow == 1) {
					rerenderItemList(request, response);
				}
			}
		} catch (SQLException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	private void toggleSurplus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			Optional<Item> item = itemService.getItemById(id);
			if (item.isPresent()) {
				item.get().setSurplus(!item.get().isSurplus());
				int updatedState;
				if (item.get().isSurplus()) {
					// discount come back as whole number e.g: 30 for 30%, so we need to divide it
					// by 100.
					var dr = request.getParameter("discountRate");
					Double newDR = Double.parseDouble(dr);
					item.get().setDiscountRate(newDR / 100);

					updatedState = itemService.markSurplusItem(item.get());
				} else {
					updatedState = itemService.unmarkSurplusItem(item.get());
				}

				if (updatedState == 1) {
					rerenderItemList(request, response);
				}

			}

		} catch (SQLException e) {
			log.warn(e.getLocalizedMessage());
			request.setAttribute("error", "failed to update item.");
			request.getRequestDispatcher("/pages/retailer/index.jsp").forward(request, response);
		}
	}

	private void toggleAvailable(HttpServletRequest request, HttpServletResponse response) {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			Optional<Item> item = itemService.getItemById(id);
			if (item.isPresent()) {
				log.info("Item: " + item.get().toString());
				item.get().setAvailable(!item.get().isAvailable());
				log.info("Item: " + item.get().toString());
				itemService.toggleAvailableItem(item.get());
			}

		} catch (SQLException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	private void rerenderItemList(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().setAttribute("items", itemService.getItems());
			response.sendRedirect(request.getContextPath() + "/pages/retailer/index.jsp");
		} catch (SQLException | IOException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	private void handleDelete(HttpServletRequest request, HttpServletResponse response) {
		Long itemId = Long.parseLong(request.getParameter("id"));
		try {
			int deletedRow = itemService.deleteItem(itemId);
			if (deletedRow == 1) {
				rerenderItemList(request, response);
			}
		} catch (SQLException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	private void handleCreate(HttpServletRequest request, HttpServletResponse response) {
		Enumeration<String> params = request.getParameterNames();
		Item item = new Item();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			switch (param) {
			case "name":
				item.setName(request.getParameter(param));
				break;
			case "description":
				item.setDescription(request.getParameter(param));
				break;
			case "price":
				item.setPrice(new BigDecimal(request.getParameter(param)));
				break;
			case "discountRate":
				item.setDiscountRate(Double.parseDouble(request.getParameter(param)));
				break;
			case "quantity":
				item.setQuantity(Integer.parseInt(request.getParameter(param)));
				break;
			default:
				break;
			}
		}

		try {
			Item newItem = itemService.create(item);
			if (newItem.getId() != null) {
				rerenderItemList(request, response);
			}
		} catch (SQLException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	private void handleUpdate(HttpServletRequest request, HttpServletResponse response) {
		Long itemId = Long.parseLong(request.getParameter("id"));
		try {
			Optional<Item> item = itemService.getItemById(itemId);
			if (item.isPresent()) {
				Enumeration<String> params = request.getParameterNames();
				while (params.hasMoreElements()) {
					String param = params.nextElement();
					switch (param) {
					case "name":
						item.get().setName(request.getParameter(param));
						break;
					case "description":
						item.get().setDescription(request.getParameter(param));
						break;
					case "price":
						System.out.println(request.getParameter(param));
						System.out.println(item.get().getOriginalPrice());
						item.get().setPrice(new BigDecimal(request.getParameter(param)));
						System.out.println(item.get().getOriginalPrice());
						break;
					case "discountRate":
						item.get().setDiscountRate(Double.parseDouble(request.getParameter(param)) / 100);
						break;
					case "quantity":
						item.get().setQuantity(Integer.parseInt(request.getParameter(param)));
						break;
					default:
						break;
					}
				}
				log.info(item.toString());
				int updatedRow = itemService.updateItem(item.get());
				if (updatedRow == 1) {
					rerenderItemList(request, response);
				}
			}
		} catch (SQLException e) {
			log.warn(e.getLocalizedMessage());
		}
	}
}
