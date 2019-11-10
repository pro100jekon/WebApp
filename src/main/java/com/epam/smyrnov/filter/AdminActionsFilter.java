package com.epam.smyrnov.filter;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.entity.user.Role;
import com.epam.smyrnov.entity.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/items.jsp", "/users.jsp", "/orders.jsp", "/editOrder", "/editItem", "/editUser"})
public class AdminActionsFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)servletRequest).getSession();
		User user = (User) session.getAttribute("user");
		if (user != null && user.getRole().equals(Role.ADMIN)) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			servletRequest.getRequestDispatcher(Constants.Pages.INFO_PAGE).forward(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
	}
}
