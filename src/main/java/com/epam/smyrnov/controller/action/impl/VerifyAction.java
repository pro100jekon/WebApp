package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerifyAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserService userService = (UserService) request.getServletContext().getAttribute("UserService");
		if (userService.verifyUser(request.getParameter("hash"))) {
			request.setAttribute("message", "INFO. You were successfully verified. Now you can continue shopping!");
		} else {
			request.setAttribute("message", "ERROR. This link is invalid or you are already verified.");
		}
		return Constants.Pages.INFO_PAGE;
	}
}
