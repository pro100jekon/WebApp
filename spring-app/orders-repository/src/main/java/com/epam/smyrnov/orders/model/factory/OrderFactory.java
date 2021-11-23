package com.epam.smyrnov.orders.model.factory;

import com.epam.smyrnov.orders.model.Order;
import com.epam.smyrnov.orders.model.ItemSummary;
import com.epam.smyrnov.orders.model.OrderedItem;
import com.epam.smyrnov.orders.model.UserSummary;

public interface OrderFactory {

    Order createOrder();

    OrderedItem createOrderedItem();

    ItemSummary createItemSummary();

    UserSummary createUserSummary();
}
