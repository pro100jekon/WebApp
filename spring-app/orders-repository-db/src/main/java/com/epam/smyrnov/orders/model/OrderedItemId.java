package com.epam.smyrnov.orders.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderedItemId implements Serializable {

    Long orderId;
    Long itemId;
}
