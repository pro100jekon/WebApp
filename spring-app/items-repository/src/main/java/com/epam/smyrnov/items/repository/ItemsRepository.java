package com.epam.smyrnov.items.repository;

import com.epam.smyrnov.items.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemsRepository {

    Item add(Item item);

    Item findById(Long id);

    List<Item> findAll();

    Item update(Long id, Item item);

    void delete(Long id);

    List<? extends Item> findAllByCategory(String category);

    List<? extends Item> findAllByColor(String color);

    List<? extends Item> findAllByPriceBetween(BigDecimal left, BigDecimal right);
}
