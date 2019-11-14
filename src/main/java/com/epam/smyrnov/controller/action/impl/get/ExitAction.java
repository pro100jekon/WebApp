package com.epam.smyrnov.controller.action.impl.get;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.ResponseType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ExitAction implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return new ActionResult(Constants.Pages.MAIN_PAGE, ResponseType.REDIRECT);
	}
}
