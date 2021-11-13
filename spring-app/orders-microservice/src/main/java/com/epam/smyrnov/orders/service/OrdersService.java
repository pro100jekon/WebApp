package com.epam.smyrnov.orders.service;

import com.epam.smyrnov.orders.model.dto.request.OrderRequest;
import com.epam.smyrnov.orders.model.dto.response.OrderResponse;

import java.util.List;

public interface OrdersService {

    List<OrderResponse> getAll();

    OrderResponse get(Long id);

    OrderResponse add(OrderRequest item);

    OrderResponse update(Long id, OrderRequest item);
}
