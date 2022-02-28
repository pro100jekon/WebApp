package com.epam.smyrnov.items.repository;

import com.epam.smyrnov.items.mapper.InternalItemsMapper;
import com.epam.smyrnov.items.model.Item;
import com.epam.smyrnov.items.model.JpaItem;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DbItemsRepository implements ItemsRepository<JpaItem> {

    private final InternalItemsRepository repository;
    private final InternalItemsMapper mapper;

    @Override
    public JpaItem add(Item item) {
        return repository.save((JpaItem) item);
    }

    @Override
    public JpaItem findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<JpaItem> findAll() {
        return Lists.newArrayList(
                repository.findAll());
    }

    @Override
    public JpaItem update(Long id, Item item) {
        return repository.save(
                mapper.map((JpaItem) item, findById(id)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<JpaItem> findAllByCategory(String category) {
        return repository.findAllByCategory(category);
    }

    @Override
    public List<JpaItem> findAllByColor(String color) {
        return repository.findAllByColor(color);
    }

    @Override
    public List<JpaItem> findAllByPriceBetween(BigDecimal left, BigDecimal right) {
        return repository.findAllByPriceBetween(left, right);
    }
}
