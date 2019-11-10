package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.order.Order;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AccountAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if  (user != null) {
			OrderService orderService = (OrderService) request.getServletContext().getAttribute("OrderService");
			List<Order> orders = orderService.getOrdersByUserId(user.getId());
			request.setAttribute("orders", orders);
			return Constants.Pages.ACCOUNT_PAGE;
		} else {
			request.setAttribute("message", "ERROR. You are not logged in. Log in and try again.");
			return Constants.Pages.INFO_PAGE;
		}
	}
}
