package com.epam.smyrnov.orders.model.factory;

import com.epam.smyrnov.orders.model.ItemSummary;
import com.epam.smyrnov.orders.model.JpaItemSummary;
import com.epam.smyrnov.orders.model.JpaOrder;
import com.epam.smyrnov.orders.model.JpaOrderedItem;
import com.epam.smyrnov.orders.model.JpaUserSummary;
import com.epam.smyrnov.orders.model.Order;
import com.epam.smyrnov.orders.model.OrderedItem;
import com.epam.smyrnov.orders.model.UserSummary;
import org.springframework.stereotype.Component;

@Component
public class JpaOrderFactory implements OrderFactory {
    @Override
    public Order createOrder() {
        return JpaOrder.empty();
    }

    @Override
    public OrderedItem createOrderedItem() {
        return new JpaOrderedItem();
    }

    @Override
    public ItemSummary createItemSummary() {
        return new JpaItemSummary();
    }

    @Override
    public UserSummary createUserSummary() {
        return new JpaUserSummary();
    }
}
