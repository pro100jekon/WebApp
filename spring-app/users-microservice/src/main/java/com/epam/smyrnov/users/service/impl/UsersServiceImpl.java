package com.epam.smyrnov.users.service.impl;

import com.epam.smyrnov.users.mapper.UserMapper;
import com.epam.smyrnov.users.model.User;
import com.epam.smyrnov.users.model.dto.request.UserRequest;
import com.epam.smyrnov.users.model.dto.response.UserResponse;
import com.epam.smyrnov.users.repository.UsersRepository;
import com.epam.smyrnov.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository<? extends User> repository;
    private final UserMapper mapper;

    @Override
    public List<UserResponse> getAll() {
        return mapper.mapToResponse(repository.findAll());
    }

    @Override
    public UserResponse get(Long id) {
        return mapper.mapToResponse(
                repository.findById(id));
    }

    @Override
    public UserResponse add(UserRequest item) {
        return mapper.mapToResponse(
                repository.add(
                        mapper.mapToEntity(item)));
    }

    @Override
    public UserResponse update(Long id, UserRequest item) {
        return mapper.mapToResponse(
                repository.update(id, mapper.mapToEntity(item)));
    }

    @Override
    public void remove(Long id) {
        repository.delete(id);
    }
}
