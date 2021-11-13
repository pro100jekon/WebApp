package com.epam.smyrnov.orders.repository;

import com.epam.smyrnov.orders.model.JpaOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpringOrdersRepository extends CrudRepository<JpaOrder, Long> {

    List<JpaOrder> findAllByUserId(Long userId);
}
