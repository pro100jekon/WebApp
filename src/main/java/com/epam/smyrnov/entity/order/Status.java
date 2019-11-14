package com.epam.smyrnov.entity.order;

import com.epam.smyrnov.controller.action.Page;

public enum Status {
    REGISTERED(Page.RESOURCE_BUNDLE.getString("registered")), CONFIRMED(Page.RESOURCE_BUNDLE.getString("confirmed")),
    ON_ITS_WAY(Page.RESOURCE_BUNDLE.getString("on.its.way")), DELIVERED(Page.RESOURCE_BUNDLE.getString("delivered")),
    PAID(Page.RESOURCE_BUNDLE.getString("paid")), CANCELLED(Page.RESOURCE_BUNDLE.getString("cancelled"));

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
