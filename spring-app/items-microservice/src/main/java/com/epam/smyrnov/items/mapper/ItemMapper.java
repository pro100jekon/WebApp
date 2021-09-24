package com.epam.smyrnov.items.mapper;

import com.epam.smyrnov.items.model.dto.jpa.Item;
import com.epam.smyrnov.items.model.dto.request.ItemRequest;
import com.epam.smyrnov.items.model.dto.response.ItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ItemMapper {

    ItemResponse mapToResponse(Item item);

    Item mapToEntity(ItemRequest request);

    Item mapToEntity(ItemRequest request, @MappingTarget Item item);

    List<ItemResponse> mapToResponse(List<Item> item);
}
