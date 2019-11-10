package com.epam.smyrnov.filter;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.entity.Cart;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/main.jsp", "/main"})
public class MainPageFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		ItemService itemService = (ItemService) request.getServletContext().getAttribute("ItemService");
		String category = request.getParameter("category");
		if (category != null) {
			session.setAttribute("items", itemService.getItemsByCategory(category));
		} else {
			session.setAttribute("items", itemService.getAllItems());
		}
		if (session.isNew()) {
			session.setAttribute("cart", new Cart());
		}
		filterChain.doFilter(request, response);
	}
}
