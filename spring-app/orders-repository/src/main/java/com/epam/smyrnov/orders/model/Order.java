package com.epam.smyrnov.orders.model;

import java.util.List;

public interface Order {

    Long getId();

    void setId(Long id);

    List<? extends OrderedItem> getOrderedItems();

    void setOrderedItems(List<? extends OrderedItem> orderedItems);

    Long getUserId();

    void setUserId(Long userId);

    UserSummary getUserSummary();

    void setUserSummary(UserSummary userSummary);

    Status getStatus();

    void setStatus(Status status);
}
