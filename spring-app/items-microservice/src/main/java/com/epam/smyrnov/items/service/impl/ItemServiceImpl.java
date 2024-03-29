package com.epam.smyrnov.items.service.impl;

import com.epam.smyrnov.items.mapper.ItemMapper;
import com.epam.smyrnov.items.model.dto.request.ItemRequest;
import com.epam.smyrnov.items.model.dto.response.ItemResponse;
import com.epam.smyrnov.items.repository.ItemsRepository;
import com.epam.smyrnov.items.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository repository;
    private final ItemMapper mapper;

    @Override
    public List<ItemResponse> getAll() {
        return mapper.mapToResponse(repository.findAll());
    }

    @Override
    public List<ItemResponse> getAllByCategory(String category) {
        return mapper.mapToResponse(
                repository.findAllByCategory(category));
    }

    @Override
    public List<ItemResponse> getAllByColor(String color) {
        return mapper.mapToResponse(
                repository.findAllByColor(color));
    }

    @Override
    public List<ItemResponse> getAllByPriceRange(BigDecimal left, BigDecimal right) {
        return mapper.mapToResponse(
                repository.findAllByPriceBetween(left, right));
    }

    @Override
    public ItemResponse get(Long id) {
        return mapper.mapToResponse(
                repository.findById(id));
    }

    @Override
    public ItemResponse add(ItemRequest item) {
        return mapper.mapToResponse(
                repository.add(
                        mapper.mapToEntity(item)));
    }

    @Override
    public ItemResponse update(Long id, ItemRequest item) {
        return mapper.mapToResponse(
                repository.update(id, mapper.mapToEntity(item)));
    }

    @Override
    public void remove(Long id) {
        repository.delete(id);
    }
}
