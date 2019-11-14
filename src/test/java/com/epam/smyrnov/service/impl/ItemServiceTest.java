package com.epam.smyrnov.service.impl;

import com.epam.smyrnov.entity.Item;
import com.epam.smyrnov.service.ItemService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemServiceTest {

	@Test
	public void testGetItemsByColor() {
		List<Item> items = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Item item = new Item();
			item.setPrice(new BigDecimal(1500).multiply(new BigDecimal(i + 1)));
			items.add(item);
		}

		ItemService itemService = new ItemServiceImpl();
		List<Item> actualList = itemService.getItemsByPriceRange(items, new BigDecimal(3333), new BigDecimal(5555));
		List<Item> expectedList = new ArrayList<>();
		Item expectedItem = new Item();
		expectedItem.setPrice(new BigDecimal(4500));
		expectedList.add(expectedItem);
		assertEquals(expectedList, actualList);
	}
}
