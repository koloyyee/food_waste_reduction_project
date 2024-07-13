package cst8288.project.fwrp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


// for login we will need to do authentication with doFilter 
// https://www.geeksforgeeks.org/servlet-authentication-filter/
/**
 * Servlet implementation class AuthController
 * user login, logout 
 */
@WebServlet(name="AuthController", urlPatterns="/auth/*")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String route = request.getPathInfo();
		switch(route) {
		case "/login":

	        response.setContentType("text/html"); 
	        PrintWriter out = response.getWriter(); 
	  
	        out.print("welcome to GEEKSFORGEEKS"); 
			break;
		}
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
