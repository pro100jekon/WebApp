package com.epam.smyrnov.controller.action.impl.post;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;
import com.epam.smyrnov.controller.action.ResponseType;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.util.HashingSha256;
import com.epam.smyrnov.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInAction implements Action {
    @Override
    public ActionResult exec(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.logged.in"));
        } else {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (email == null || password == null) {
                request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.invalid.email.and.password"));
            } else {
                ServletContext servletContext = request.getServletContext();
                UserService userService = (UserService) servletContext.getAttribute("UserService");
                password = HashingSha256.hash(password);
                User user = userService.getUserByEmail(email);
                if (user == null || !password.equals(user.getPassword())) {
                    request.setAttribute("message", Page.RESOURCE_BUNDLE.getString("err.no.such.user"));
                } else {
                    session.setAttribute("user", user);
                    return new ActionResult(Constants.Pages.MAIN_PAGE, ResponseType.REDIRECT);
                }
            }
        }
        return new ActionResult(Constants.Pages.INFO_PAGE);
    }
}
