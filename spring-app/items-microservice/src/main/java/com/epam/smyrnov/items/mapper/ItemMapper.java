package com.epam.smyrnov.items.mapper;

import com.epam.smyrnov.items.model.factory.ItemFactory;
import com.epam.smyrnov.items.model.Item;
import com.epam.smyrnov.items.model.dto.request.ItemRequest;
import com.epam.smyrnov.items.model.dto.response.ItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class ItemMapper {

    @Autowired
    private ItemFactory itemFactory;

    public abstract ItemResponse mapToResponse(Item item);

    public abstract Item mapToEntity(ItemRequest request);

    public abstract List<ItemResponse> mapToResponse(List<? extends Item> item);

    @ObjectFactory
    public Item createItem() {
        return itemFactory.create();
    }
}
