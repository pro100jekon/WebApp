package com.epam.smyrnov.util;

import com.epam.smyrnov.entity.Item;

import java.math.BigDecimal;

public class ValidateItem {

	private ValidateItem(){
	}

	public static boolean isValid(Item item) {
		boolean hasNegativeSize = item.getSize().contains("-");
		boolean hasNegativeWeight = item.getWeight() < 0;
		boolean hasNegativePrice = item.getPrice().compareTo(BigDecimal.ZERO) < 0;
		return hasNegativeSize && hasNegativeWeight && hasNegativePrice;
	}
}
