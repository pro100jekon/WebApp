package com.epam.smyrnov.entity;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart extends Entity {

    private static final long serialVersionUID = -3218674865410897865L;
    private Map<Item, Integer> itemInCart = new HashMap<>();

    public Map<Item, Integer> getItemInCart() {
        return itemInCart;
    }

    public void put(Item item, Integer quantity) {
        itemInCart.put(item, quantity);
    }

    public void put(Item item) {
        itemInCart.put(item, 1);
    }

    public void remove(Item item) {
        itemInCart.remove(item);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal sum = new BigDecimal(0);
        for (Map.Entry<Item, Integer> entry : itemInCart.entrySet()) {
            sum = sum.add(getPriceOfOneItem(entry));
        }
        return sum;
    }

    public boolean isEmpty() {
        return itemInCart.isEmpty();
    }

    public BigDecimal getPriceOfOneItem(Map.Entry<Item, Integer> entry) {
        return entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue()));
    }
}
