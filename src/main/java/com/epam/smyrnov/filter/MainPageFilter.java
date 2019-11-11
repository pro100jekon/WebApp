package com.epam.smyrnov.filter;

import com.epam.smyrnov.service.ItemService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/main.jsp", "/main"})
public class MainPageFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		request.setAttribute("items", itemService.getAllItems());
		filterChain.doFilter(request, response);
	}
}
