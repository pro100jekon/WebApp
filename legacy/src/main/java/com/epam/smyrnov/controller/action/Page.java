package com.epam.smyrnov.controller.action;

import com.epam.smyrnov.filter.LocaleFilter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Page {

	private static final Logger logger = Logger.getLogger(Page.class);

	public static ResourceBundle RESOURCE_BUNDLE;
	static {
		RESOURCE_BUNDLE = ResourceBundle.getBundle("locale", Locale.ENGLISH, LocaleFilter.class.getClassLoader());
	}

	private HttpServletRequest request;
	private HttpServletResponse response;

	public Page(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void navigate(ActionResult result) {
		System.out.println("result is: " + result.getOutput());
		try {
			if (result.getResponseType().equals(ResponseType.REDIRECT)) {
				if (request.getAttribute("message") != null) {
					response.sendRedirect(result.getOutput().substring(1) + "?message=" + request.getAttribute("message"));
				} else {
					response.sendRedirect(result.getOutput().substring(1));
				}
			} else if (result.getResponseType().equals(ResponseType.FORWARD)){
				request.getRequestDispatcher(result.getOutput()).forward(request, response);
			} else {
				response.setCharacterEncoding("UTF-8");
				response.getWriter().println(result.getOutput());
			}
		} catch (IOException | ServletException ex) {
			logger.error("Dispatcher failed: ", ex);
		}
	}
}
