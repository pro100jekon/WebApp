package com.epam.smyrnov.orders.model.factory;

import com.epam.smyrnov.orders.model.Order;
import com.epam.smyrnov.orders.model.OrderedItem;

public interface OrderFactory {

    Order createOrder();

    OrderedItem createOrderedItem();
}
