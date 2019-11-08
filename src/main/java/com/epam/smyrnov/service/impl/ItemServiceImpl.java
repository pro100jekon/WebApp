package com.epam.smyrnov.service.impl;

import com.epam.smyrnov.annotation.Autowired;
import com.epam.smyrnov.annotation.Service;
import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.repository.ItemRepository;
import com.epam.smyrnov.repository.impl.ItemRepositoryImpl;
import com.epam.smyrnov.service.ItemService;
import com.epam.smyrnov.constants.SQLQueries;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {

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
	public List<Item> getItemsByColorFromOneCategory(String category, String color) {
		return repository.findAllByColorFromOneCategory(category, color);
	}

	@Override
	public List<Item> getItemsByPriceRangeFromOneCategory(String category, BigDecimal left, BigDecimal right) {
		return repository.findAllByPriceRangeFromOneCategory(category, left, right);
	}

	@Override
	public List<Item> getItemsByWeightFromOneCategory(String category, int weight) {
		return repository.findAllByWeightFromOneCategory(category, weight);
	}

	@Override
	public List<String> getAllCategories() {
		return repository.getAllCategories();
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
	public boolean removeItem(Item item) {
		return repository.delete(item.getId());
	}
}
