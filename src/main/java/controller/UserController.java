package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UserController.
 * <br>
 * Handlers user login, logout, register, delete
 */
@WebServlet(name="UserControoller", urlPatterns="/users/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * Create user:<br>
	 * get username, password, email, phone?, type as int.
	 * */
	private void register(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password= request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone")
		int userTypeInt = Integer.parseInt(request.getParameter("type"));
	}

}
