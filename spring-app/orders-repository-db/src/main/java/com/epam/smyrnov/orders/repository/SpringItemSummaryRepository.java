package com.epam.smyrnov.orders.repository;

import com.epam.smyrnov.orders.model.JpaItemSummary;
import org.springframework.data.repository.CrudRepository;

public interface SpringItemSummaryRepository extends CrudRepository<JpaItemSummary, Long> {
}
