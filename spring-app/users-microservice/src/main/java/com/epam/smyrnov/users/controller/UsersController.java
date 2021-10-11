package com.epam.smyrnov.users.controller;

import com.epam.smyrnov.users.model.dto.request.UserRequest;
import com.epam.smyrnov.users.model.dto.response.UserResponse;
import com.epam.smyrnov.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;

    @GetMapping
    // for admin todo
    public List<UserResponse> getAll() {
        return service.getAll();
    }

    @PatchMapping("{id}")
    // execute if id matches with auth principal todo
    public UserResponse updatePersonalInfo(@PathVariable Long id, @RequestBody UserRequest request) {
        return service.update(id, request);
    }

    @GetMapping("{id}")
    // execute if id matches with auth principal todo
    public UserResponse getById(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("{id}")
    // execute if id matches with auth principal todo
    public void deleteById(@PathVariable Long id) {
        service.remove(id);
    }
}
