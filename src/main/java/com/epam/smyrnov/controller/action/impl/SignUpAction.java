package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignUpAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			request.setAttribute("message", "ERROR. You can't register while logged in. Exit your account first.");
		} else {
			return Constants.Pages.REGISTRATION_PAGE;
		}
		return Constants.Pages.INFO_PAGE;
	}
}
