package com.epam.smyrnov.orders.repository;

import com.epam.smyrnov.orders.mapper.InternalOrdersMapper;
import com.epam.smyrnov.orders.model.JpaOrder;
import com.epam.smyrnov.orders.model.Order;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DbOrdersRepository implements OrdersRepository<JpaOrder> {

    private final SpringOrdersRepository repository;
    private final InternalOrdersMapper mapper;

    @Override
    public JpaOrder add(Order order) {
        return repository.save((JpaOrder) order);
    }

    @Override
    public JpaOrder findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public JpaOrder update(Long orderId, Order order) {
        return repository.save(
                mapper.map(
                        (JpaOrder) order,
                        repository.findById(orderId).orElseThrow()));
    }

    @Override
    public List<JpaOrder> findAll() {
        return Lists.newArrayList(
                repository.findAll(PageRequest.ofSize(20)));
    }

    @Override
    public List<? extends JpaOrder> findAll(Integer page) {
        return repository.findAll(PageRequest.of(page, 20));
    }

    @Override
    public List<JpaOrder> findAllByUserId(Long userId) {
        return Lists.newArrayList(
                repository.findAllByUserId(userId));
    }
}
