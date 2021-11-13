package com.epam.smyrnov.orders.model;

import java.util.Set;

public interface Order {

    Long getId();

    void setId(Long id);

    Set<? extends OrderedItem> getOrderedItems();

    void setOrderedItems(Set<? extends OrderedItem> orderedItems);

    Long getUserId();

    void setUserId(Long userId);

    Status getStatus();

    void setStatus(Status status);
}
