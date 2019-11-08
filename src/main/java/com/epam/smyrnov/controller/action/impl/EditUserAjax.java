package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserAjax implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = request.getParameter("userId");
		String block = request.getParameter("block");
		UserService userService = (UserService) request.getServletContext().getAttribute("UserService");
		User user = userService.getUserById(Long.parseLong(userId));
		boolean bool = Boolean.parseBoolean(block);
		if (bool) {
			System.out.println("unblock");
			user = userService.unblockUser(user);
		} else {
			System.out.println("block");
			user = userService.blockUser(user);
		}
		StringBuilder outerInfo = new StringBuilder();
		outerInfo.append("User id: ").append(user.getId()).append("<br>");
		outerInfo.append("First name: ").append(user.getFirstName()).append("<br>");
		outerInfo.append("Last name: ").append(user.getLastName()).append("<br>");
		outerInfo.append("Email: ").append(user.getEmail()).append("<br>");
		outerInfo.append("Blocked: ").append(user.isBlocked()).append("<br>");
		outerInfo.append("Role: ").append(user.getRole().value()).append("<br>");
		return "ajaxOutput/" + outerInfo.toString();
	}
}
