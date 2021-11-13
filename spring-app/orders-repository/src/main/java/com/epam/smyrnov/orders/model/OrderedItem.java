package com.epam.smyrnov.orders.model;

import java.math.BigDecimal;

public interface OrderedItem {

    Long getItemId();

    void setItemId(Long itemId);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Integer getQuantity();

    void setQuantity(Integer quantity);
}
