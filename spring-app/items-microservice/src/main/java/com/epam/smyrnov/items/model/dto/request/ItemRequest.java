package com.epam.smyrnov.items.model.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {

    String category;
    String name;
    BigDecimal price;
    Date dateOfAddition;
    String size;
    String color;
    Integer weight;
}
