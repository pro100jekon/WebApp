package com.epam.smyrnov.items.model.factory;

import com.epam.smyrnov.items.model.Item;
import com.epam.smyrnov.items.model.JpaItem;
import org.springframework.stereotype.Component;

@Component
public class JpaItemFactory implements ItemFactory {

    @Override
    public Item create() {
        return JpaItem.empty();
    }
}
