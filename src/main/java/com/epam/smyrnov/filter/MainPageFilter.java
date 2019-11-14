package com.epam.smyrnov.filter;

import com.epam.smyrnov.service.ItemService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Resets the items
 */
@WebFilter(urlPatterns = {"/main", "/main.jsp"})
public class MainPageFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		request.setAttribute("items", itemService.getAllItems());
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
