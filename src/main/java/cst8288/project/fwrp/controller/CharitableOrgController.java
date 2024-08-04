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
 *
 * Charitable Organization allowed methods: getItems, getItemById
 *  
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		switch (path) {
		case "/items":
			handleGetItem(request, response);
			break;
		case "/claim_history":
			handleGetClaimedItem(request, response);
			break;
		default:
			response.sendRedirect(request.getContextPath() + "/pages/charity/index.jsp");
			break;
		}
	}


	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getPathInfo();
		switch (path) {
		case "/items/claim":
			handleClaimItem(request, response);
			break;
		default:
			response.sendRedirect(request.getContextPath() + "/pages/charity/index.jsp");
			break;
		}
	}

	/**
     * Handle claim item with user id, item id and quantity
     * 
     * @param request
     */
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

	/**
	 * Handle get item request by id
	 * 
	 * @param request
	 * @param response
	 */
	private void handleGetItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			Long itemId = Long.parseLong(request.getParameter("id"));
			var item = itemService.getItemById(itemId);
			request.setAttribute("item", item.get());
			request.getRequestDispatcher("/pages/charity/item.jsp").forward(request, response);
//  				}
		} catch (SQLException | ServletException | IOException e) {
			log.warn(e.getLocalizedMessage());
		}
	}

	/**
	 * Return Charitable Organization claimed items
	 *
	 * @param request
	 * @param response
	 */
	private void handleGetClaimedItem(HttpServletRequest request, HttpServletResponse response) {
			
		Long uid = request.getParameter("user_id") != null ? Long.parseLong(request.getParameter("user_id"))
				: ((User) request.getSession().getAttribute("user")).getId();
		
		try {
			request.setAttribute("items", itemService.getClaimedHistory(uid));
			request.getRequestDispatcher("/pages/charity/claim_history.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			log.warn(e.getLocalizedMessage());
		}
	}
}
