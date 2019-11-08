package com.epam.smyrnov.service;

import com.epam.smyrnov.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {

	List<Item> getAllItems();

	List<Item> getItemsByCategory(String category);

	List<Item> getItemsByColorFromOneCategory(String category, String color);

	List<Item> getItemsByPriceRangeFromOneCategory(String category, BigDecimal left, BigDecimal right);

	List<Item> getItemsByWeightFromOneCategory(String category, int weight);

	List<String> getAllCategories();

	Item getItemById(Long id);

	Item addItem(Item item);

	Item updateItem(Item item);

	boolean removeItem(Item item);
}
