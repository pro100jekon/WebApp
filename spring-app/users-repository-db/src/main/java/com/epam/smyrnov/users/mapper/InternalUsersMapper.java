package com.epam.smyrnov.users.mapper;

import com.epam.smyrnov.users.model.JpaUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface InternalUsersMapper {

    JpaUser map(JpaUser source, @MappingTarget JpaUser target);
}
