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
import java.util.logging.Logger;

import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.service.UserService;

/**
 * Servlet Filter implementation class UserTypeFilter
 * This the role based authorization
 */
public class UserTypeFilter extends HttpFilter implements Filter {
	private Logger log = Logger.getLogger(UserTypeFilter.class.getName());
	private UserService userService = new UserService();

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public UserTypeFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 *      req.getContextPath() -> /fwrp <br>
	 *      req.getServletPath() -> /retailers or consumers or charitable_org <br>
	 *      req.getPathInfo() -> /hello or /anything
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		User user = (User) req.getSession().getAttribute("user");

		/**
		 * check if the user type is correct with the email, 
		 * if not just send it back to
		 * home page.
		 */
		if (user == null || !req.getServletPath().contains(user.getType().name().toLowerCase())) {
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
