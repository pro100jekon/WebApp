package com.epam.smyrnov.orders.repository;

import com.epam.smyrnov.orders.mapper.InternalOrdersMapper;
import com.epam.smyrnov.orders.model.JpaOrder;
import com.epam.smyrnov.orders.model.Order;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DbUsersRepository implements OrdersRepository {

    private final SpringOrdersRepository repository;
    private final InternalOrdersMapper mapper;

    @Override
    public Order add(Order order) {
        return repository.save((JpaOrder) order);
    }

    @Override
    public Order findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Order update(Long orderId, Order order) {
        return repository.save(
                mapper.map(
                        (JpaOrder) order,
                        repository.findById(orderId).orElseThrow()));
    }

    @Override
    public List<Order> findAll() {
        return Lists.newArrayList(
                repository.findAll());
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return Lists.newArrayList(
                repository.findAllByUserId(userId));
    }
}
