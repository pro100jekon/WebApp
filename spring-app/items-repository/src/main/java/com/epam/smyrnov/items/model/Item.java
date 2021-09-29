package com.epam.smyrnov.items.model;

import java.math.BigDecimal;
import java.sql.Date;

public interface Item {

    Long getId();

    void setId(Long id);

    String getCategory();

    void setCategory(String category);

    String getName();

    void setName(String name);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Date getDateOfAddition();

    void setDateOfAddition(Date dateOfAddition);

    String getSize();

    void setSize(String size);

    String getColor();

    void setColor(String color);

    Integer getWeight();

    void setWeight(Integer weight);
}
