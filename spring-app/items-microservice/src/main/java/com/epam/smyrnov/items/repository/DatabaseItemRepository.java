package com.epam.smyrnov.items.repository;

import com.epam.smyrnov.items.model.dto.jpa.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DatabaseItemRepository extends CrudRepository<Item, Long> {

    List<Item> findAllByCategory(String category);

    List<Item> findAllByColor(String color);

    List<Item> findAllByPriceBetween(BigDecimal left, BigDecimal right);
}
