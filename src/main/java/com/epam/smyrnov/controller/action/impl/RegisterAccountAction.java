package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.security.HashingSha256;
import com.epam.smyrnov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterAccountAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		if (email != null && password != null && firstName != null && lastName != null) {
			UserService userService = (UserService) request.getServletContext().getAttribute("UserService");
			userService.addUser(firstName, lastName, email, HashingSha256.hash(password));
			request.setAttribute("message", "INFO. Now you can log in with your account!");
			return "PRG" + Constants.Pages.INFO_PAGE;
		} else {
			request.setAttribute("message", "ERROR. Can't register account.");
		}
		return Constants.Pages.INFO_PAGE;
	}
}
