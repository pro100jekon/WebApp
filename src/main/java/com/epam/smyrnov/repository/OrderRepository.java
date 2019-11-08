package com.epam.smyrnov.repository;

import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.entity.order.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

	boolean existsById(Long id);

	Order findById(Long id);

	List<Order> findAll();

	List<Order> findAllByUserId(Long id);

	Order update(Order entity);

	Order create(Order entity);

	List<Order> saveAll(List<Order> entities);
}
