package com.epam.smyrnov.users.mapper;

import com.epam.smyrnov.users.model.User;
import com.epam.smyrnov.users.model.dto.request.UserRequest;
import com.epam.smyrnov.users.model.dto.response.UserResponse;
import com.epam.smyrnov.users.model.factory.UserFactory;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class UserMapper {

    @Autowired
    private UserFactory userFactory;

    public abstract UserResponse mapToResponse(User user);

    public abstract User mapToEntity(UserRequest request);

    public abstract List<UserResponse> mapToResponse(List<? extends User> users);

    @ObjectFactory
    public User createUser() {
        return userFactory.create();
    }
}
