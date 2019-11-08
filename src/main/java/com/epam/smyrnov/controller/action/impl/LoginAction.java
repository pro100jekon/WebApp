package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.security.HashingSha256;
import com.epam.smyrnov.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {
    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            request.setAttribute("error", "You are already logged in. Exit your account first, then try again");
        } else {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (email == null || password == null) {
                request.setAttribute("error", "Email or password is incorrect");
            } else {
                ServletContext servletContext = request.getServletContext();
                UserService userService = (UserService) servletContext.getAttribute("UserService");
                password = HashingSha256.hash(password);
                User user = userService.getUserByEmail(email);
                if (!password.equals(user.getPassword())) {
                    request.setAttribute("error", "There is no user with such email and password");
                } else {
                    session.setAttribute("user", user);
                    return "PRG" + Constants.Pages.MAIN_PAGE;
                }
            }
        }
        return Constants.Pages.ERROR_PAGE;
    }
}
