package com.epam.smyrnov.controller;

import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionFactory;
import com.epam.smyrnov.entity.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "/controller",
urlPatterns = {"/"})
public class MainController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getMethod() + request.getServletPath());
        Action action = ActionFactory.getAction(request);
        String view = action.exec(request, response);
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("cart", new Cart());
            session.setMaxInactiveInterval(60 * 60);
        }
        if (view.contains("ajaxOutput")) {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            String[] s = view.split("/");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < s.length; i++) {
                stringBuilder.append(s[i]).append("/");
            }
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("/"));
            response.getWriter().println(stringBuilder);
        } else if (view.contains("PRG")) {
                String[] s = view.split("/");
                response.sendRedirect(s[s.length - 1]);
        } else {
            request.getRequestDispatcher(view).forward(request, response);
        }
    }
}
