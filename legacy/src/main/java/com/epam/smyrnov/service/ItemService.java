package com.epam.smyrnov.service;

import com.epam.smyrnov.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {

	List<Item> getAllItems();

	List<Item> getItemsByCategory(String category);

	List<Item> getItemsByColor(List<Item> items, String color);

	List<Item> getItemsByPriceRange(List<Item> items, BigDecimal left, BigDecimal right);

	List<String> getAllCategories();

	List<String> getAllColors();

	Item getItemById(Long id);

	Item addItem(Item item);

	Item updateItem(Item item);

	boolean removeItemById(Long id);
}
