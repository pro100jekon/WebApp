package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.order.Order;
import com.epam.smyrnov.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditOrderAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("orderId");
		HttpSession session = request.getSession();
		OrderService orderService = ((OrderService) request.getServletContext().getAttribute("OrderService"));
		Order order = orderService.getOrderById(Long.parseLong(id));
		if (order != null) {
			session.setAttribute("order", order); // you can change only one order per session.
													 // Needed for validation of changes.
		}
		if (session.getAttribute("itemMap") != null) {
			session.removeAttribute("itemMap");
		}
		return Constants.Pages.ORDER_EDITOR_PAGE;
	}
}
