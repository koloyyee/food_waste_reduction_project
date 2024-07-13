package cst8288.project.fwrp.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Logger;

import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.service.UserService;

/**
 * Servlet Filter implementation class PasswordFilter
 */
public class PasswordFilter implements Filter {

	private Logger log = Logger.getLogger(PasswordFilter.class.getName());
	private UserService userService = new UserService();

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String inputPassword = request.getParameter("password");
		String inputEmail = request.getParameter("email");
		PrintWriter out = response.getWriter();
		log.info(inputPassword  + " " + inputEmail);
		try {
			User user = userService.loadUserByEmail(inputEmail);
			log.info(user.toString());

			if (inputEmail.equals(user.getEmail()) && inputPassword.equals(user.getPassword())) {
				// pass the request along the filter chain
				request.setAttribute("user", user);
				request.setAttribute("isValid", true);
				chain.doFilter(request, response);

			} else {
				request.setAttribute("errMsg","username or password is wrong" );
				request.setAttribute("isValid", false);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			}

		} catch (SQLException e) {
			log.warning(e.getLocalizedMessage());
		}

	}
	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public PasswordFilter() {
		super();
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
