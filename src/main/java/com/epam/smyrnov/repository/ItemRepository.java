package com.epam.smyrnov.repository;

import com.epam.smyrnov.entity.Item;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    boolean delete(Long id);

    boolean existsById(Long id);

    Item findById(Long id);

    List<Item> findAll();

    List<Item> findAllByCategory(String category);

    Item update(Item entity);

    Item create(Item entity);

    List<Item> saveAll(List<Item> entities);

    List<String> getAllCategories();

    List<Item> findAllByColorFromOneCategory(String category, String color);

    List<Item> findAllByPriceRangeFromOneCategory(String category, BigDecimal left, BigDecimal right);

    List<Item> findAllByWeightFromOneCategory(String category, int weight);
}
