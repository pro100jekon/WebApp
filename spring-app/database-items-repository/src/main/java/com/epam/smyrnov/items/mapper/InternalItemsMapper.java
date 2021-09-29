package com.epam.smyrnov.items.mapper;

import com.epam.smyrnov.items.model.JpaItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface InternalItemsMapper {

    JpaItem map(JpaItem source, @MappingTarget JpaItem target);
}
