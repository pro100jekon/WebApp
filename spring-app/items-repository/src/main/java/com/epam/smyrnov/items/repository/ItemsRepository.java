package com.epam.smyrnov.items.repository;

import com.epam.smyrnov.items.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemsRepository<I extends Item> {

    I add(I item);

    I findById(Long id);

    List<? extends I> findAll();

    I update(Long id, I item);

    void delete(Long id);

    List<? extends I> findAllByCategory(String category);

    List<? extends I> findAllByColor(String color);

    List<? extends I> findAllByPriceBetween(BigDecimal left, BigDecimal right);
}
