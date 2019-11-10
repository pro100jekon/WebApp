package com.epam.smyrnov.controller.action.impl;

import com.epam.smyrnov.constants.Constants;
import com.epam.smyrnov.controller.action.Action;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.entity.order.DeliveryType;
import com.epam.smyrnov.entity.order.Order;
import com.epam.smyrnov.entity.order.PaymentType;
import com.epam.smyrnov.entity.order.Status;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.service.OrderService;
import com.epam.smyrnov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class SaveOrderAction implements Action {
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
		OrderService orderService = (OrderService) request.getServletContext().getAttribute("OrderService");
		UserService userService = (UserService) request.getServletContext().getAttribute("UserService");
		HttpSession session = request.getSession();
		Order sessionOrder = (Order) session.getAttribute("order");
		if (sessionOrder != null) {
			Order orderForSave = new Order();
			Long id = Long.parseLong(request.getParameter("orderId"));
			User user = userService.getUserById(Long.parseLong(request.getParameter("userId")));
			Status status = Status.values()[Integer.parseInt(request.getParameter("statusId"))];
			DeliveryType deliveryType = DeliveryType.values()[Integer.parseInt(request.getParameter("deliveryTypeId"))];
			PaymentType paymentType = PaymentType.values()[Integer.parseInt(request.getParameter("paymentTypeId"))];
			if (sessionOrder.getId().equals(id) && sessionOrder.getUser().equals(user)) {
				orderForSave.setId(id);
				orderForSave.setUser(user);
			}
			if (sessionOrder.getStatus().equals(Status.CANCELLED) ||
					(sessionOrder.getStatus().equals(Status.CONFIRMED) && status.equals(Status.REGISTERED)) ||
					(sessionOrder.getStatus().equals(Status.REGISTERED) && status.equals(Status.REGISTERED))) { //cannot change cancelled orders AND cannot revert confirmed into registered
				request.setAttribute("message", "ERROR. Invalid state of order status to be changed.");
			} else {
				orderForSave.setStatus(status);
				orderForSave.setDeliveryType(deliveryType);
				orderForSave.setPaymentType(paymentType);
				Map<Item, Integer> itemMap = (Map<Item, Integer>) session.getAttribute("itemMap");
				if (itemMap != null) { // if map is not empty, it means it has at least one item and its quantity
					orderForSave.setItemsAndQuantities(itemMap);
					orderService.updateOrder(orderForSave);
					request.setAttribute("info", "INFO");
					return "PRG" + Constants.Pages.ALL_ORDERS_PAGE;
				} else {
					request.setAttribute("message", "ERROR. Items are empty.");
				}
			}
		} else {
			request.setAttribute("message", "ERROR. There is no order to edit.");
		}
		return Constants.Pages.INFO_PAGE;
	}
}
