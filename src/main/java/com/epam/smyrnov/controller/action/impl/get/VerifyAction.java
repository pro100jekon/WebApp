package com.epam.smyrnov.controller.action.impl.get;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;
import com.epam.smyrnov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerifyAction implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserService userService = (UserService) request.getServletContext().getAttribute("UserService");
		if (userService.verifyUser(request.getParameter("hash"))) {
			request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("mes.successfully.verified"));
		} else {
			request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.invalid.link"));
		}
		return new ActionResult(Constants.Pages.INFO_PAGE);
	}
}
