package com.epam.smyrnov.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Defines and sets current locale.
 */
@WebFilter(urlPatterns = {"*"})
public class LocaleFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String currentLocale = (String) servletRequest.getSession().getAttribute("currentLocale");
		if (currentLocale == null) {
			currentLocale = "en";
			servletRequest.getSession().setAttribute("currentLocale", currentLocale);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}