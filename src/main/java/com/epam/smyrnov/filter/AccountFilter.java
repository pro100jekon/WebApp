package com.epam.smyrnov.filter;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Doesn't let to access account page if not logged in.
 */
@WebFilter(urlPatterns = {"/account"})
public class AccountFilter implements Filter {

	private static final Logger logger = Logger.getLogger(AccountFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("AccountFilter was initialized");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)servletRequest).getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			servletRequest.getRequestDispatcher(Constants.Pages.INFO_PAGE).forward(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
		logger.debug("AccountFilter was destroyed");
	}
}
