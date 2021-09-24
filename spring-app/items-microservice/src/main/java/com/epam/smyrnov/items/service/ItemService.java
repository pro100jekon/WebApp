package com.epam.smyrnov.items.service;

import com.epam.smyrnov.items.model.dto.request.ItemRequest;
import com.epam.smyrnov.items.model.dto.response.ItemResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {

    List<ItemResponse> getAll();

    List<ItemResponse> getAllByCategory(String category);

    List<ItemResponse> getAllByColor(String color);

    List<ItemResponse> getAllByPriceRange(BigDecimal left, BigDecimal right);

    ItemResponse get(Long id);

    ItemResponse add(ItemRequest item);

    ItemResponse update(Long id, ItemRequest item);

    void remove(Long id);
}
