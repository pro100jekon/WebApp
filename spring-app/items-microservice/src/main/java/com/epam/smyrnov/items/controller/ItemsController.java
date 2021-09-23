package com.epam.smyrnov.items.controller;

import com.epam.smyrnov.items.model.Item;
import com.epam.smyrnov.items.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("items")
public class ItemsController {

    @Autowired
    ItemRepository repository;

    @GetMapping("{id}")
    public Item doSmth(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }
}
