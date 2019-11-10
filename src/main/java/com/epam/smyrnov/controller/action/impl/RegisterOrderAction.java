package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Cart;
import com.epam.smyrnov.entity.order.DeliveryType;
import com.epam.smyrnov.entity.order.Order;
import com.epam.smyrnov.entity.order.PaymentType;
import com.epam.smyrnov.entity.order.Status;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterOrderAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				OrderService orderService = (OrderService) request.getServletContext().getAttribute("OrderService");
				String deliveryType = request.getParameter("deliveryType");
				String paymentType = request.getParameter("paymentType");
				if (deliveryType != null && paymentType != null) {
					Order order = new Order();
					Long id = orderService.getLastId();
					order.setId(id);
					order.setUser(user);
					order.setDeliveryType(DeliveryType.values()[Integer.parseInt(deliveryType)]);
					order.setPaymentType(PaymentType.values()[Integer.parseInt(paymentType)]);
					order.setItemsAndQuantities(((Cart) session.getAttribute("cart")).getItemInCart());
					order.setStatus(Status.REGISTERED);
					orderService.addOrder(order);
					session.removeAttribute("cart");
					session.setAttribute("cart", new Cart());
					return "PRG" + Constants.Pages.CART_PAGE;
				} else {
					request.setAttribute("message", "ERROR. Wrong params. Try again.");
				}
			} else {
				request.setAttribute("message", "ERROR. You are not logged in. Try again.");
			}
		} else {
			request.setAttribute("message", "ERROR. There is no order to register");
		}
		return Constants.Pages.INFO_PAGE;
	}
}
