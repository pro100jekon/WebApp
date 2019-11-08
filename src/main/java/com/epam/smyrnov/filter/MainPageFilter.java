package com.epam.smyrnov.filter;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.entity.Cart;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/main.jsp"})
public class MainPageFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) servletRequest).getSession();
		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", new Cart());
			//servletRequest.getRequestDispatcher(Constants.Pages.MAIN_PAGE);
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
			//servletRequest.getRequestDispatcher(Constants.Pages.MAIN_PAGE);
		}
	}
}
