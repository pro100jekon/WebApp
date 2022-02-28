package com.epam.smyrnov.items.controller;

import com.epam.smyrnov.items.model.dto.request.ItemRequest;
import com.epam.smyrnov.items.model.dto.response.ItemResponse;
import com.epam.smyrnov.items.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemsController {

    private final ItemService service;
    private final KafkaTemplate<String, Object> template;

    @GetMapping
    public List<ItemResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ItemResponse add(@RequestBody ItemRequest item) {
        return service.add(item);
    }

    @GetMapping("{id}")
    public ItemResponse getById(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }

    @PatchMapping("{id}")
    public ItemResponse partialUpdate(@PathVariable Long id, @RequestBody ItemRequest item) {
        var result = service.update(id, item);
        template.send("items-db-test", result);
        return result;
    }
}
