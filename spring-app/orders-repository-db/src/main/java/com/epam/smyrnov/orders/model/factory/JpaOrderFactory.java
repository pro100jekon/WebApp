package com.epam.smyrnov.orders.model.factory;

import com.epam.smyrnov.orders.model.JpaOrder;
import com.epam.smyrnov.orders.model.Order;
import com.epam.smyrnov.orders.model.OrderedItem;
import org.springframework.stereotype.Component;

@Component
public class JpaOrderFactory implements OrderFactory {
    @Override
    public Order createOrder() {
        return JpaOrder.empty();
    }

    @Override
    public OrderedItem createOrderedItem() {
        return null;
    }
}
