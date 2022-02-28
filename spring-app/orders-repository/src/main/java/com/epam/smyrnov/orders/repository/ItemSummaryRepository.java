package com.epam.smyrnov.orders.repository;

import com.epam.smyrnov.orders.model.ItemSummary;

public interface ItemSummaryRepository<IS extends ItemSummary> {

    IS update(Long id, ItemSummary itemSummary);
}
