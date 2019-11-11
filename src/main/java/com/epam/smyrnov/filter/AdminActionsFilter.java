package com.epam.smyrnov.filter;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.entity.user.Role;
import com.epam.smyrnov.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/items.jsp", "/users.jsp", "/orders.jsp", "/addItem.jsp", "/deleteItem.jsp", "/editOrder", "/editItem"})
public class AdminActionsFilter implements Filter {

	private static final Logger logger = Logger.getLogger(AdminActionsFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("AdminActionsFilter was initialized");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)servletRequest).getSession();
		User user = (User) session.getAttribute("user");
		if (user != null && user.getRole().equals(Role.ADMIN)) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			servletRequest.setAttribute("message", "You don't have permissions to do that.");
			servletRequest.getRequestDispatcher(Constants.Pages.INFO_PAGE).forward(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
		logger.debug("AdminActionsFilter was destroyed");
	}
}
