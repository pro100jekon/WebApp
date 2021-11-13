package com.epam.smyrnov.orders.service.impl;

import com.epam.smyrnov.orders.mapper.OrderMapper;
import com.epam.smyrnov.orders.model.dto.request.OrderRequest;
import com.epam.smyrnov.orders.model.dto.response.OrderResponse;
import com.epam.smyrnov.orders.repository.OrdersRepository;
import com.epam.smyrnov.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository repository;
    private final OrderMapper mapper;

    @Override
    public List<OrderResponse> getAll() {
        return mapper.mapToResponse(repository.findAll());
    }

    @Override
    public OrderResponse get(Long id) {
        return mapper.mapToResponse(
                repository.findById(id));
    }

    @Override
    public OrderResponse add(OrderRequest item) {
        return mapper.mapToResponse(
                repository.add(
                        mapper.mapToEntity(item)));
    }

    @Override
    public OrderResponse update(Long id, OrderRequest item) {
        return mapper.mapToResponse(
                repository.update(id, mapper.mapToEntity(item)));
    }
}
