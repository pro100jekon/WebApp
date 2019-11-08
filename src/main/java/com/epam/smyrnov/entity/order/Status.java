package com.epam.smyrnov.entity.order;

public enum Status {
    REGISTERED("Registered"), CONFIRMED("Confirmed"), ON_ITS_WAY("On its way"), DELIVERED("Delivered"), PAID("Paid"), CANCELLED("Cancelled");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
