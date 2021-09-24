package com.epam.smyrnov.items.model.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    String id;
    String category;
    String name;
    BigDecimal price;
    Date dateOfAddition;
    String size;
    String color;
    Integer weight;
}
