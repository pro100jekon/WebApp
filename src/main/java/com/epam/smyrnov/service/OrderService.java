package com.epam.smyrnov.service;

import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.entity.order.Order;

import java.util.List;

public interface OrderService {

	List<Order> getAllOrders();

	List<Order> getOrdersByUserId(Long id);

	List<Order> addOrders(List<Order> orders);

	Order getOrderById(Long id);

	Order addOrder(Order order);

	Order updateOrder(Order order);

	Long getLastId();
}
