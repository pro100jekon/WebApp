package com.epam.smyrnov.orders.controller;

import com.epam.smyrnov.orders.model.dto.response.OrderResponse;
import com.epam.smyrnov.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService service;

    @GetMapping
    // for admin todo
    public List<OrderResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    // execute if id matches with auth principal todo
    public OrderResponse getById(@PathVariable Long id) {
        return service.get(id);
    }
}
