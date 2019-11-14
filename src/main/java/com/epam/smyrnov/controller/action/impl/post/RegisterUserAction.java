package com.epam.smyrnov.controller.action.impl.post;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;
import com.epam.smyrnov.controller.action.ResponseType;
import com.epam.smyrnov.mail.EmailVerification;
import com.epam.smyrnov.util.HashingSha256;
import com.epam.smyrnov.service.UserService;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class RegisterUserAction implements Action {
	@Override
	public ActionResult exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
				request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("mes.successfully.registered"));
				} else {
					request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.email.exists"));
				}
			return new ActionResult(Constants.Pages.INFO_PAGE, ResponseType.REDIRECT);
		} else {
			request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.cannot.register"));
		}
		return new ActionResult(Constants.Pages.INFO_PAGE);
	}
}
