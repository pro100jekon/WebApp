package com.epam.smyrnov.orders.mapper;

import com.epam.smyrnov.orders.model.JpaOrder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface InternalOrdersMapper {

    JpaOrder map(JpaOrder source, @MappingTarget JpaOrder target);
}
