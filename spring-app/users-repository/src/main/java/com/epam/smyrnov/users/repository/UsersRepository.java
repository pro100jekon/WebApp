package com.epam.smyrnov.users.repository;

import com.epam.smyrnov.users.model.User;

import java.util.List;

public interface UsersRepository<U extends User> {

    U add(User user);

    U findById(Long id);

    U findByEmail(String email);

    List<? extends U> findAll();

    U update(Long id, User user);

    void delete(Long id);
}
