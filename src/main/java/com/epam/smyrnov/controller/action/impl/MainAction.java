package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Cart;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static javax.swing.text.html.CSS.getAttribute;

public class MainAction implements Action {
    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "/main.jsp";
    }
}
