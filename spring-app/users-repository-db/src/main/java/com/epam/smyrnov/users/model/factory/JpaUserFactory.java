package com.epam.smyrnov.users.model.factory;

import com.epam.smyrnov.users.model.JpaUser;
import com.epam.smyrnov.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class JpaUserFactory implements UserFactory {
    @Override
    public User create() {
        return JpaUser.empty();
    }
}
