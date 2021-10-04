package com.epam.smyrnov.users.repository;

import com.epam.smyrnov.users.model.User;

import java.util.List;

public interface UsersRepository {

    User add(User user);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();

    User update(Long id, User user);

    void delete(Long id);
}
