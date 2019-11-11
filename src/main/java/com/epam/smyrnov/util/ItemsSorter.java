package com.epam.smyrnov.util;

import com.epam.smyrnov.entity.Item;

import java.util.Comparator;
import java.util.List;

public class ItemsSorter {

	private ItemsSorter() {
	}

	public static final Comparator<Item> SORT_BY_NAME_ASC =
			(Item o1, Item o2) -> o1.getName().toLowerCase()
					.compareTo(o2.getName().toLowerCase());

	public static final Comparator<Item> SORT_BY_NAME_DESC =
			(Item o1, Item o2) -> o2.getName().toLowerCase()
					.compareTo(o1.getName().toLowerCase());

	public static final Comparator<Item> SORT_BY_DATE_ASC =
			(Item o1, Item o2) -> o1.getDate().compareTo(o2.getDate());

	public static final Comparator<Item> SORT_BY_DATE_DESC =
			(Item o1, Item o2) -> o2.getDate().compareTo(o1.getDate());


	public static final Comparator<Item> SORT_BY_PRICE_ASC =
			(Item o1, Item o2) -> o1.getPrice().compareTo(o2.getPrice());

	public static final Comparator<Item> SORT_BY_PRICE_DESC =
			(Item o1, Item o2) -> o2.getPrice().compareTo(o1.getPrice());

	public static final Comparator<Item> SORT_BY_WEIGHT_ASC =
			(Item o1, Item o2) -> Integer.compare(o1.getWeight(), o2.getWeight());

	public static final Comparator<Item> SORT_BY_WEIGHT_DESC =
			(Item o1, Item o2) -> Integer.compare(o2.getWeight(), o1.getWeight());


	public static void sortByName(List<Item> items, boolean desc) {
		if (!desc) {
			items.sort(SORT_BY_NAME_ASC);
		} else {
			items.sort(SORT_BY_NAME_DESC);
		}
	}

	public static void sortByDate(List<Item> items, boolean desc) {
		if (!desc) {
			items.sort(SORT_BY_DATE_ASC);
		} else {
			items.sort(SORT_BY_DATE_DESC);
		}
	}

	public static void sortByPrice(List<Item> items, boolean desc) {
		if (!desc) {
			items.sort(SORT_BY_PRICE_ASC);
		} else {
			items.sort(SORT_BY_PRICE_DESC);
		}
	}

	public static void sortByWeight(List<Item> items, boolean desc) {
		if (!desc) {
			items.sort(SORT_BY_WEIGHT_ASC);
		} else {
			items.sort(SORT_BY_WEIGHT_DESC);
		}
	}
}
