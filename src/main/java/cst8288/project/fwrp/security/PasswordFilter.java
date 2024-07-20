package cst8288.project.fwrp.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// clean up any previous error message
		req.getSession().setAttribute("errMsg", null);

		String inputPassword = request.getParameter("password");
		String inputEmail = req.getParameter("email");
//		log.info(inputPassword + " " + inputEmail);
		try {
			User user = userService.loadUserByEmail(inputEmail);

			if (inputEmail.equals(user.getEmail()) && inputPassword.equals(user.getPassword())) {
				// pass the request along the filter chain
				request.setAttribute("user", user);
				request.setAttribute("isValid", true);
				chain.doFilter(req, resp);

			} else {
				String msg = "username or password is wrong";
				req.getSession().setAttribute("errMsg",msg );
				resp.sendRedirect(req.getContextPath() + "/index.jsp");
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
