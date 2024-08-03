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

import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.service.UserService;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;

/**
 * Servlet Filter implementation class PasswordFilter
 */
public class PasswordFilter implements Filter {

//	private Logger log = Logger.getLogger(PasswordFilter.class.getName());
	private Logger log = LoggerFactory.getLogger();
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

			if ( user != null  && inputEmail.toLowerCase().equals(user.getEmail().toLowerCase())
					&& inputPassword.equals(user.getPassword())) {
				// pass the request along the filter chain
				request.setAttribute("user", user);
				request.setAttribute("isValid", true);
				chain.doFilter(req, resp);

			} else {
				String msg = "username or password is wrong";
				req.getSession().setAttribute("errMsg", msg);
			var val = 	req.getSession().getAttribute("errMsg");
				req.getSession().getAttribute("errMsg");
				resp.sendRedirect(req.getContextPath() + "/pages/login.jsp");
			}

		} catch (SQLException e) {
			log.warn(e.getLocalizedMessage());
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

	// for testing
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
