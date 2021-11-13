package com.epam.smyrnov.orders.mapper;

import com.epam.smyrnov.orders.model.Order;
import com.epam.smyrnov.orders.model.OrderedItem;
import com.epam.smyrnov.orders.model.dto.request.OrderRequest;
import com.epam.smyrnov.orders.model.dto.response.OrderResponse;
import com.epam.smyrnov.orders.model.factory.OrderFactory;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class OrderMapper {

    @Autowired
    private OrderFactory orderFactory;

    public abstract OrderResponse mapToResponse(Order order);

    public abstract Order mapToEntity(OrderRequest request);

    public abstract List<OrderResponse> mapToResponse(List<? extends Order> orders);

    @ObjectFactory
    public Order createOrder() {
        return orderFactory.createOrder();
    }
}
