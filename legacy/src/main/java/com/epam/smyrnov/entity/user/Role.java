package com.epam.smyrnov.entity.user;

import java.util.Locale;

public enum Role {

    ADMIN("admin"), CLIENT("client");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return this.value.toLowerCase(Locale.getDefault());
    }
}
