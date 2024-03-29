package com.epam.smyrnov.orders.model.dto.response;

import com.epam.smyrnov.orders.model.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    UserResponse user;
    List<OrderedItemResponse> orderedItems;
    Status status;
}
