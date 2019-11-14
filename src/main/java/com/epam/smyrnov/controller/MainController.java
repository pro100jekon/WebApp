package com.epam.smyrnov.controller;

import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.controller.action.ActionFactory;
import com.epam.smyrnov.controller.action.ActionResult;
import com.epam.smyrnov.controller.action.Page;
import com.epam.smyrnov.entity.Cart;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller that processes every entered url connected with the app except for @code{ImageServlet}
 */
@WebServlet(name = "/controller",
urlPatterns = {"/"})
public class MainController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Action action = ActionFactory.getAction(request);
        ActionResult result = action.exec(request, response);
        Page page = new Page(request, response);
        page.navigate(result);
    }
}
