package com.epam.smyrnov.users.controller;

import com.epam.smyrnov.users.model.dto.response.UserResponse;
import com.epam.smyrnov.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;

    @GetMapping
    public List<UserResponse> getAll() {
        return service.getAll();
    }
}
