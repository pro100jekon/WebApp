package com.epam.smyrnov.orders.repository;

import com.epam.smyrnov.orders.mapper.InternalOrdersMapper;
import com.epam.smyrnov.orders.model.ItemSummary;
import com.epam.smyrnov.orders.model.JpaItemSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DbItemSummaryRepository implements ItemSummaryRepository<JpaItemSummary> {

    private final SpringItemSummaryRepository itemSummaryRepository;
    private final InternalOrdersMapper mapper;

    @Override
    public JpaItemSummary update(Long id, ItemSummary itemSummary) {
        return itemSummaryRepository.save(
                mapper.map(
                        (JpaItemSummary) itemSummary,
                        itemSummaryRepository.findById(id).orElseThrow()));
    }
}
