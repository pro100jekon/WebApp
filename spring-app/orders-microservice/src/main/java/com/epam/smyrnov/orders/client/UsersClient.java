package com.epam.smyrnov.orders.client;

import com.epam.smyrnov.orders.model.entity.response.UserEntityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "users-ms")
@RequestMapping("users")
public interface UsersClient {

    @GetMapping
    List<UserEntityResponse> getUsers();

    @GetMapping("{userId}")
    UserEntityResponse getUser(@PathVariable Long userId);
}
