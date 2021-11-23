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
public class DbUsersRepository implements UsersRepository<JpaUser> {

    private final SpringUsersRepository repository;
    private final InternalUsersMapper mapper;

    @Override
    public JpaUser add(User user) {
        return repository.save((JpaUser) user);
    }

    @Override
    public JpaUser findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public JpaUser findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<JpaUser> findAll() {
        return Lists.newArrayList(
                repository.findAll());
    }

    @Override
    public JpaUser update(Long id, User user) {
        return repository.save(
                mapper.map((JpaUser) user, findById(id)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
