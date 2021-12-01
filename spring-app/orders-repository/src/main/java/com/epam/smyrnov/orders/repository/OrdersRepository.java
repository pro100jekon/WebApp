package com.epam.smyrnov.orders.repository;

import com.epam.smyrnov.orders.model.Order;

import java.util.List;

public interface OrdersRepository<O extends Order> {

    O add(Order order);

    O findById(Long id);

    O update(Long orderId, Order order);

    List<? extends O> findAll();

    List<? extends O> findAll(Integer page);

    List<? extends O> findAllByUserId(Long userId);
}
