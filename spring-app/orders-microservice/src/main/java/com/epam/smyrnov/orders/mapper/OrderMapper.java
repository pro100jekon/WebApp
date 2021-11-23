package com.epam.smyrnov.orders.mapper;

import com.epam.smyrnov.orders.model.ItemSummary;
import com.epam.smyrnov.orders.model.Order;
import com.epam.smyrnov.orders.model.OrderedItem;
import com.epam.smyrnov.orders.model.UserSummary;
import com.epam.smyrnov.orders.model.dto.request.OrderRequest;
import com.epam.smyrnov.orders.model.dto.response.OrderResponse;
import com.epam.smyrnov.orders.model.dto.response.OrderedItemResponse;
import com.epam.smyrnov.orders.model.dto.response.UserResponse;
import com.epam.smyrnov.orders.model.entity.response.ItemEntityResponse;
import com.epam.smyrnov.orders.model.entity.response.UserEntityResponse;
import com.epam.smyrnov.orders.model.factory.OrderFactory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    @Mapping(target = "user", source = "userSummary")
    public abstract OrderResponse mapToResponse(Order order);

    public <T extends Order> T mapToEntity(OrderRequest request) {
        return (T) map(request);
    }

    abstract Order map(OrderRequest request);

    public abstract List<OrderResponse> mapToResponse(List<? extends Order> orders);

    public abstract List<ItemSummary> mapItemSummary(List<ItemEntityResponse> entityResponses);

    public abstract ItemSummary mapItemSummary(ItemEntityResponse response);

    public abstract UserSummary mapUserSummary(UserEntityResponse response);

    public abstract UserResponse mapUserResponse(UserSummary userSummary);

    @Mapping(target = "name", source = "orderedItem.itemSummary.name")
    public abstract OrderedItemResponse mapOrderedItemResponse(OrderedItem orderedItem);

    @ObjectFactory
    public Order createOrder() {
        return orderFactory.createOrder();
    }

    @ObjectFactory
    public ItemSummary createItemSummary() {
        return orderFactory.createItemSummary();
    }

    @ObjectFactory
    public UserSummary createUserSummary() {
        return orderFactory.createUserSummary();
    }
}
