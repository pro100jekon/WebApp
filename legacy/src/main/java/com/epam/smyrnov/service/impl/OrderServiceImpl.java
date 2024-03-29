package com.epam.smyrnov.service.impl;

import com.epam.smyrnov.annotation.Autowired;
import com.epam.smyrnov.annotation.Service;
import com.epam.smyrnov.entity.order.Order;
import com.epam.smyrnov.repository.OrderRepository;
import com.epam.smyrnov.service.OrderService;

import java.io.Serializable;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService, Serializable {

	private static final long serialVersionUID = -6545646156749866472L;
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> getOrdersByUserId(Long id) {
		return orderRepository.findAllByUserId(id);
	}

	@Override
	public List<Order> addOrders(List<Order> orders) {
		return orderRepository.saveAll(orders);
	}

	@Override
	public Order updateOrder(Order order) {
		return orderRepository.update(order);
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public Order addOrder(Order order) {
		return orderRepository.create(order);
	}

	@Override
	public Long setNewLastId() {
		return orderRepository.getLastId() + 1;
	}
}
