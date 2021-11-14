package com.epam.smyrnov.users.repository;

import com.epam.smyrnov.users.model.User;

import java.util.List;

public interface UsersRepository<U extends User> {

    U add(U user);

    U findById(Long id);

    U findByEmail(String email);

    List<? extends U> findAll();

    U update(Long id, U user);

    void delete(Long id);
}
