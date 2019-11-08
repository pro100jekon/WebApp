package com.epam.smyrnov.service.impl;

import com.epam.smyrnov.annotation.Autowired;
import com.epam.smyrnov.annotation.Service;
import com.epam.smyrnov.entity.order.Order;
import com.epam.smyrnov.repository.OrderRepository;
import com.epam.smyrnov.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

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
	public List<Order> getOrdersByItemId(Long id) {
		return null;
		//return orderRepository.findOrdersByItemId(id);
	}

	@Override
	public List<Order> addOrders(List<Order> orders) {
		return orderRepository.saveAll(orders);
	}//TODO delete if not needed

	@Override
	public Order updateOrder(Order order) {
		return null;
		//return orderRepository.save(order, true);
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public Order addOrder(Order order) {
		return orderRepository.update(order);
	}
}
