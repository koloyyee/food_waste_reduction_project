package cst8288.project.fwrp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class RetailerController<br>
 *  
 * <ul>
 *   <li>[] View all items: if the item is 1 week before expiry, it should show as surplus.</li>
 *   <li>[] View all surplus</li>
 *   <li>[] Create, update, and delete items</li>
 *   <li>[] Change item status as donated, purchased</li>
 *   <li>[] Set if the item is for “donation”</li>
 *   <li>[] Set quantities</li>
 *   <li>[] Set discount rate</li>
 * </ul>
 */
@WebServlet(name = "RetailerController", urlPatterns = { "/retailers/*" })
public class RetailerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetailerController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getPathInfo();
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
