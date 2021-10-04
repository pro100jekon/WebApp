package com.epam.smyrnov.users.service;

import com.epam.smyrnov.users.model.dto.request.UserRequest;
import com.epam.smyrnov.users.model.dto.response.UserResponse;

import java.util.List;

public interface UsersService {

    List<UserResponse> getAll();

    UserResponse get(Long id);

    UserResponse add(UserRequest item);

    UserResponse update(Long id, UserRequest item);

    void remove(Long id);
}
