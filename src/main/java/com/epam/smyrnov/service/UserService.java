package com.epam.smyrnov.service;

import com.epam.smyrnov.entity.user.User;
import com.epam.smyrnov.entity.user.Role;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    List<User> getUsersByRole(Role role);

    User getUserByEmail(String email);

    User getUserById(Long id);

    User addUser(String firstName, String lastName, String email, String password); //TODO change parameters if needed

    User updateUser(User user);

    boolean verifyUser(String hash);

    User blockUser(User user);

    User unblockUser(User user);
}
