package com.epam.smyrnov.items.repository;

import com.epam.smyrnov.items.model.JpaItem;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface InternalItemsRepository extends CrudRepository<JpaItem, Long> {

    List<JpaItem> findAllByCategory(String category);

    List<JpaItem> findAllByColor(String color);

    List<JpaItem> findAllByPriceBetween(BigDecimal left, BigDecimal right);
}
