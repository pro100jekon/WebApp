package com.epam.smyrnov.orders.mapper;

import com.epam.smyrnov.orders.model.JpaOrder;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE
)
public interface InternalOrdersMapper {

    JpaOrder map(JpaOrder source, @MappingTarget JpaOrder target);
}
