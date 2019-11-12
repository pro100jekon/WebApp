package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.mail.EmailVerification;
import com.epam.smyrnov.util.HashingSha256;
import com.epam.smyrnov.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class RegisterUserAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		if (email != null && password != null && firstName != null && lastName != null) {
			ServletContext servletContext = request.getServletContext();
			UserService userService = (UserService) servletContext.getAttribute("UserService");
			System.out.println(HashingSha256.hash(password));
			if (userService.addUser(firstName, lastName, email, HashingSha256.hash(password)) != null) {
				new Thread(() -> EmailVerification.sendVerificationMail(
						email, (Properties) servletContext.getAttribute("properties"))).start();
				request.setAttribute("message", "INFO. Now you can log in with your account!" +
						" The verification mail was sent to the registered email. Check it out to continue shopping!");
				} else {
					request.setAttribute("message", "ERROR. This email is already registered.");
				}
			return "PRG" + Constants.Pages.INFO_PAGE;
		} else {
			request.setAttribute("message", "ERROR. Can't register account.");
		}
		return Constants.Pages.INFO_PAGE;
	}
}
