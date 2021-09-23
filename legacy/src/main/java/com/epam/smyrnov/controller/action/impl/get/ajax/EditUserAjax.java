package com.epam.smyrnov.controller.action.impl.get.ajax;

import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.ResponseType;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserAjax implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = request.getParameter("userId");
		String block = request.getParameter("block");
		UserService userService = (UserService) request.getServletContext().getAttribute("UserService");
		User user = userService.getUserById(Long.parseLong(userId));
		boolean bool = Boolean.parseBoolean(block);
		if (bool) {
			user = userService.unblockUser(user);
		} else {
			user = userService.blockUser(user);
		}
		return new ActionResult(((Boolean)user.isBlocked()).toString(), ResponseType.AJAX);
	}
}
