package com.epam.smyrnov.orders.service;

import com.epam.smyrnov.orders.model.dto.request.OrderRequest;
import com.epam.smyrnov.orders.model.dto.response.OrderResponse;
import org.springframework.lang.Nullable;

import java.util.List;

public interface OrdersService {

    List<OrderResponse> getAll(@Nullable Integer page);

    OrderResponse get(Long id);

    OrderResponse add(OrderRequest item);

    OrderResponse update(Long id, OrderRequest item);
}
