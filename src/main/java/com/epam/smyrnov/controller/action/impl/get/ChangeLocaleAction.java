package com.epam.smyrnov.controller.action.impl.get;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;
import com.epam.smyrnov.controller.action.ResponseType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLocaleAction implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String language = request.getParameter("language");
		if (session.getAttribute("currentLocale") != language) {
			if (language.equals("en") || language.equals("ru")) {
				session.setAttribute("currentLocale", language);
				Locale locale = new Locale(language);
				Page.RESOURCE_BUNDLE = ResourceBundle.getBundle("locale", locale, Page.class.getClassLoader());
			}
		}
		return new ActionResult(Constants.Pages.MAIN_PAGE, ResponseType.REDIRECT);
	}
}
