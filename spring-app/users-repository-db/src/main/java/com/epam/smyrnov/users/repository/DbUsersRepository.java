package com.epam.smyrnov.users.repository;

import com.epam.smyrnov.users.mapper.InternalUsersMapper;
import com.epam.smyrnov.users.model.JpaUser;
import com.epam.smyrnov.users.model.User;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DbUsersRepository implements UsersRepository {

    private final SpringUsersRepository repository;
    private final InternalUsersMapper mapper;

    @Override
    public User add(User user) {
        return repository.save((JpaUser) user);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return Lists.newArrayList(
                repository.findAll());
    }

    @Override
    public User update(Long id, User user) {
        return repository.save(
                mapper.map((JpaUser) user, (JpaUser) findById(id)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
