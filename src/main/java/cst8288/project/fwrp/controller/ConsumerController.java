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
import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.service.ItemService;
import cst8288.project.fwrp.utils.Logger;

/**
 * Servlet implementation class ConsumerController
 * Consumer allowed methods: getAllItems, getItem, subscribe, order, unsubscribe, getOrderHistory 
 */
@WebServlet(name = "ConsumerController", urlPatterns = { "/consumers/items/*", "/consumers/users/*", "/consumers/*" })
public class ConsumerController extends HttpServlet {
	private Logger log = Logger.getLogger();
	private static final long serialVersionUID = 1L;
	private ItemService itemService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsumerController() {
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
		log.info("Path: " + path);
		switch (path) {
		case "/items/all":
			handleGetAllItems(request, response);
			break;
		case "/items":
			handleGetItem(request, response);
			break;
		case "/subscribe":
			handleUserSubscribed(request, response);
			break;
		case "/order_history":
			handleGetOrderHistory(request, response);
			break;
		default:

			response.sendRedirect(request.getContextPath() + "/pages/consumer/index.jsp");
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// handle post request to order the surplus items
		String path = request.getPathInfo();
		log.info("Path: " + path);
		switch (path) {
		case "/items/order":
			handleOrder(request, response);
			break;
		case "/items/subscribe":
			handleSubscribe(request, response);
			break;
		case "/items/unsubscribe":
			handleUnsubscribe(request, response);
			break;
		default:
			response.sendRedirect(request.getContextPath() + "/pages/consumer/index.jsp");
			break;
		}
	}

	private void handleGetOrderHistory(HttpServletRequest request, HttpServletResponse response) {
		Long uid = request.getParameter("user_id") != null ? Long.parseLong(request.getParameter("user_id"))
				: ((User) request.getSession().getAttribute("user")).getId();

		try {
			request.setAttribute("items", itemService.getConsumerOrderHistory(uid));
			request.getRequestDispatcher("/pages/consumer/order_history.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	/**
	 * Get all items can be view by Consumers
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
     */
	private void handleGetAllItems(HttpServletRequest request, HttpServletResponse response) {
		try {
			var items = itemService.getConsumerItems();
			request.getSession().setAttribute("items", items);
			response.sendRedirect(request.getContextPath() + "/pages/consumer/index.jsp");
//            request.getRequestDispatcher("/pages/consumer/index.jsp").forward(request, response);
		} catch (SQLException | IOException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	/**
	 * Get item by Id
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void handleGetItem(HttpServletRequest request, HttpServletResponse response) {
		String param = request.getParameter("id");
		try {
			var item = itemService.getItemById(Long.parseLong(param));
			if (!item.isPresent()) {
				response.sendRedirect("/pages/consumer/items.jsp");
			} else {
				request.setAttribute("item", item.get());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/consumer/item.jsp");
				dispatcher.forward(request, response);
			}
		} catch (NumberFormatException | SQLException | ServletException | IOException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	
	/**
	 * Get Consumer's subscribed items
	 * 
	 * @param request
	 * @param response
	 */
	private void handleUserSubscribed(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().setAttribute("items", null);
			long userId = Long.parseLong(request.getParameter("user_id"));

			var items = itemService.getSubscribedItems(userId);
			request.setAttribute("items", items);
			request.getRequestDispatcher("/pages/consumer/subscribed.jsp").forward(request, response);
		} catch (SQLException | IOException | ServletException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	/**
	 * Handle Consumer's order
	 * 
	 * deduct the quantity from the item table
	 * 
	 *  insert to order table
	 *  
	 * @param request
	 * @param response
	 */
	private void handleOrder(HttpServletRequest request, HttpServletResponse response) {
		String param = request.getParameter("id");
		try {
			

			User user = (User) request.getSession().getAttribute("user");
			Long userId = user.getId();
			Long itemId = Long.parseLong(param);
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			double discountedPrice = Double.parseDouble(request.getParameter("discounted_price"));

			int result = itemService.orderSurplusItem(userId, itemId, quantity, discountedPrice);
			if (result < 0) {
				request.setAttribute("errMsg", "Failed to order item");
				handleGetItem(request, response);
			} else {
				request.setAttribute("msg", "Item ordered successfully");
				response.sendRedirect(request.getContextPath() + "/pages/consumer/order_placed.jsp");
			}

		} catch (NumberFormatException | SQLException | IOException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	/**
	 * Handle user subscribe to an item and get notification if the product is mark as surplus
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * */
	public void handleSubscribe(HttpServletRequest request, HttpServletResponse response) {
		Long itemId = Long.parseLong(request.getParameter("item_id"));
		User user = (User) request.getSession().getAttribute("user");

		try {
			int result = itemService.subscribeItem(itemId, user.getId());
			if (result < 0) {
				request.setAttribute("errMsg", "Failed to subscribe item");
				handleGetItem(request, response);
			} else {
				request.setAttribute("msg", "Item subscribed successfully");
				response.sendRedirect(request.getContextPath() + "/pages/consumer/index.jsp");
			}
		} catch (NumberFormatException | IOException | SQLException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	/**
	 *  Handling user unsubscribing from an item
	 *  
	 *  @param request
	 *  @param response
	 *  @throws IOException
	 *  @throws ServletException
	 *  
	 * */
	public void handleUnsubscribe(HttpServletRequest request, HttpServletResponse response) {
		Long itemId = Long.parseLong(request.getParameter("item_id"));
		User user = (User) request.getSession().getAttribute("user");

		try {
			int result = itemService.unsubscribeItem(itemId, user.getId());
			if (result < 0) {
				request.setAttribute("errMsg", "Failed to subscribe item");
				handleGetItem(request, response);
			} else {
				request.setAttribute("items", itemService.getSubscribedItems(user.getId()));
				request.setAttribute("msg", "Item subscribed successfully");
				request.getRequestDispatcher("/pages/consumer/subscribed.jsp").forward(request, response);
			}

		} catch (NumberFormatException | IOException | SQLException | ServletException e) {
			log.warn(e.getLocalizedMessage());
		}
	}
}
