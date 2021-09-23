package com.epam.smyrnov.controller.action.impl.get;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignUpAction implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.cannot.register.when.logged.in"));
		} else {
			return new ActionResult(Constants.Pages.REGISTRATION_PAGE);
		}
		return new ActionResult(Constants.Pages.INFO_PAGE);
	}
}
