package com.epam.smyrnov.service.impl;

import com.epam.smyrnov.annotation.Autowired;
import com.epam.smyrnov.annotation.Service;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.repository.ItemRepository;
import com.epam.smyrnov.repository.impl.ItemRepositoryImpl;
import com.epam.smyrnov.service.ItemService;
import com.epam.smyrnov.constants.SQLQueries;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService, Serializable {

	private static final long serialVersionUID = -5641321654798745646L;
	@Autowired
	private	ItemRepository repository;

	@Override
	public List<Item> getAllItems() {
		return repository.findAll();
	}

	@Override
	public List<Item> getItemsByCategory(String category) {
		return repository.findAllByCategory(category);
	}

	@Override
	public List<Item> getItemsByColor(List<Item> items, String color) {
		items.removeIf(item -> !item.getColor().toLowerCase().equals(color.toLowerCase()));
		return items;
	}

	@Override
	public List<Item> getItemsByPriceRange(List<Item> items, BigDecimal left, BigDecimal right) {
		items.removeIf(item -> !(item.getPrice().compareTo(left) >= 0 && item.getPrice().compareTo(right) <= 0));
		return items;
	}

	@Override
	public List<String> getAllCategories() {
		return repository.getAllCategories();
	}

	@Override
	public List<String> getAllColors() {
		return repository.getAllColors();
	}

	@Override
	public Item addItem(Item item) {
		return repository.create(item);
	}

	@Override
	public Item getItemById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Item updateItem(Item item) {
		return repository.update(item);
	}

	@Override
	public boolean removeItemById(Long id) {
		return repository.delete(id);
	}
}
