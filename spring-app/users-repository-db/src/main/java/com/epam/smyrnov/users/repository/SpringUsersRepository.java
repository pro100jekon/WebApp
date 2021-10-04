package com.epam.smyrnov.users.repository;

import com.epam.smyrnov.users.model.JpaUser;
import org.springframework.data.repository.CrudRepository;

public interface SpringUsersRepository extends CrudRepository<JpaUser, Long> {

    JpaUser findByEmail(String email);
}
