package com.epam.smyrnov.service.impl;

import com.epam.smyrnov.annotation.Autowired;
import com.epam.smyrnov.annotation.Service;
import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.repository.UserRepository;
import com.epam.smyrnov.util.HashingSha256;
import com.epam.smyrnov.service.UserService;
import com.epam.smyrnov.entity.user.Role;

import java.io.Serializable;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, Serializable {

    private static final long serialVersionUID = 4568764135134687987L;
    @Autowired
    private UserRepository repository;

    public UserServiceImpl() {}

    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public List<User> getUsersByRole(Role role)  {
        return repository.findAllByRole(role);
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User addUser(String firstName, String lastName, String email, String hashedPassword) {
        User user = new User();
        user.setRole(Role.CLIENT);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return repository.create(user);
    }

    @Override
    public User updateUser(User user) {
        return repository.update(user);
    }

    @Override
    public boolean verifyUser(String hash) {
        return repository.verifyUser(hash);
    }

    @Override
    public User blockUser(User user) {
        user.setBlocked(true);
        return repository.update(user);
    }

    @Override
    public User unblockUser(User user) {
        user.setBlocked(false);
        return repository.update(user);
    }
}
