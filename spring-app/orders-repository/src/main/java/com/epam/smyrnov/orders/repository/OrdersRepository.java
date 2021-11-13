package com.epam.smyrnov.orders.repository;

import com.epam.smyrnov.orders.model.Order;
import com.epam.smyrnov.orders.model.Status;

import java.util.List;

public interface OrdersRepository {

    Order add(Order order);

    Order findById(Long id);

    Order update(Long orderId, Order order);

    List<Order> findAll();

    List<Order> findAllByUserId(Long userId);
}
