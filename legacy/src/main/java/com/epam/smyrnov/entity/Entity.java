package com.epam.smyrnov.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
