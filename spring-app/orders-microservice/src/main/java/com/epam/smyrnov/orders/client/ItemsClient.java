package com.epam.smyrnov.orders.client;

import com.epam.smyrnov.orders.model.entity.response.ItemEntityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("items-ms")
@RequestMapping("items")
public interface ItemsClient {

    @GetMapping
    List<ItemEntityResponse> getItems();
}
