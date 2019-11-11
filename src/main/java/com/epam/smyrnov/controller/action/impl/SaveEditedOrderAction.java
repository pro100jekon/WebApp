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
import java.util.LinkedHashMap;
import java.util.Map;

public class SaveEditedOrderAction implements Action {
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
			Status status;
			DeliveryType deliveryType;
			PaymentType paymentType;
			if (!request.getParameter("statusId").equals("none")) {
				status = Status.values()[Integer.parseInt(request.getParameter("statusId"))];
			} else {
				status = sessionOrder.getStatus();
			}
			if (!request.getParameter("deliveryTypeId").equals("none")) {
				deliveryType = DeliveryType.values()[Integer.parseInt(request.getParameter("deliveryTypeId"))];
			} else {
				deliveryType = sessionOrder.getDeliveryType();
			}
			if (!request.getParameter("paymentTypeId").equals("none")) {
				paymentType = PaymentType.values()[Integer.parseInt(request.getParameter("paymentTypeId"))];
			} else {
				paymentType = sessionOrder.getPaymentType();
			}
			if (sessionOrder.getId().equals(id) && sessionOrder.getUser().equals(user)) {
				orderForSave.setId(id);
				orderForSave.setUser(user);
			}
			if (sessionOrder.getStatus().equals(Status.CANCELLED) ||
					(sessionOrder.getStatus().equals(Status.CONFIRMED) && status.equals(Status.REGISTERED))) {
				//cannot change cancelled orders AND cannot revert confirmed into registered
				request.setAttribute("message", "ERROR. Invalid state of order status to be changed.");
			} else {
				orderForSave.setStatus(status);
				orderForSave.setDeliveryType(deliveryType);
				orderForSave.setPaymentType(paymentType);
				Map<Item, Integer> itemMap = (Map<Item, Integer>) session.getAttribute("itemMap");
				if (itemMap == null) { // if map is not empty, it means it has at least one item and its quantity
					itemMap = sessionOrder.getItemsAndQuantities();
				}
				orderForSave.setItemsAndQuantities(itemMap);
				orderService.updateOrder(orderForSave);
				request.setAttribute("message", "INFO. Order was successfully edited.");
			}
		} else {
			request.setAttribute("message", "ERROR. There is no order to edit.");
		}
		return "PRG" + Constants.Pages.INFO_PAGE;
	}
}
