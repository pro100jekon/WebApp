package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainAction implements Action {
    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("cart", new Cart());
        }
        return "/main.jsp";
    }
}
