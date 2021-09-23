package com.epam.smyrnov.repository;

import com.epam.smyrnov.entity.user.Role;
import com.epam.smyrnov.entity.user.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean delete(Long id);

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();

    List<User> findAllByRole(Role role);

    User update(User entity);

    User create(User entity);

    List<User> saveAll(List<User> entities);

    boolean verifyUser(String hash);
}
