package cst8288.project.fwrp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.service.ItemService;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;

/**
 * Servlet implementation class CharitableOrgController
 */
@WebServlet(name = "CharitableOrgController", urlPatterns = { "/charitable_orgs/*", "/charitable_orgs" })
public class CharitableOrgController extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger();
	private ItemService itemService;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CharitableOrgController() {
		super();
		this.itemService = new ItemService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// TODO: needs testing
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		switch (path) {
		case "/items":
			handleGetItem(request, response);
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// TODO: needs testing
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getPathInfo();
		switch (path) {
		case "/items/claim":
			handleClaimItem(request, response);
			break;
		}
	}

	// TODO: needs testing
	private void handleClaimItem(HttpServletRequest request, HttpServletResponse response) {
		try {
            Long itemId = Long.parseLong(request.getParameter("id"));
            User user = (User) request.getSession().getAttribute("user");
            Long userId = user.getId();
            int quantity = Integer.parseInt(request.getParameter("quantity"));
           var result  =  itemService.claimItem(itemId, userId, quantity);
           if ( result < 0 ) {
				request.setAttribute("errMsg", "Failed to order item");
				handleGetItem(request, response);
           } else {
				request.setAttribute("msg", "Item ordered successfully");
				response.sendRedirect(request.getContextPath() + "/pages/charity/order_placed.jsp");
           }
        } catch (SQLException | IOException e) {
            log.warn(e.getLocalizedMessage());
        }
	}

	// TODO: needs testing
	private void handleGetItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			Long itemId = Long.parseLong(request.getParameter("id"));
			var item = itemService.getItemById(itemId);
//  				if(!item.isPresent()) {
//					response.sendRedirect("/pages/charity/index.jsp");
//  				}  else {
			request.setAttribute("item", item.get());
			request.getRequestDispatcher("/pages/charity/item.jsp").forward(request, response);
//  				}
		} catch (SQLException | ServletException | IOException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

}
