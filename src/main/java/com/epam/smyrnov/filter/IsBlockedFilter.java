package com.epam.smyrnov.filter;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.entity.Cart;
import com.epam.smyrnov.entity.user.Role;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.service.ItemService;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class IsBlockedFilter implements Filter {

	private static final Logger logger = Logger.getLogger(IsBlockedFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("IsBlockedFilter was initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User) session.getAttribute("user");
		if (user != null && user.isBlocked() && user.getRole().equals(Role.CLIENT) && !((HttpServletRequest) request).getServletPath().equals("/exit")) {
			request.setAttribute("message", "ERROR. You are blocked");
			request.getRequestDispatcher(Constants.Pages.INFO_PAGE).forward(request, response);
		} else {
			filterChain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		logger.debug("IsBlockedFilter was destroyed");
	}
}
