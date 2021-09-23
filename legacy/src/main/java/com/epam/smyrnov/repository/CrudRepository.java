package com.epam.smyrnov.repository;

import java.util.List;

public interface CrudRepository<T, ID> {

    long getLastId();

    boolean delete(ID id);

    boolean existsById(ID id);

    T findById(ID id);

    List<T> findAll();

    T update(T entity);

    T create(T entity);

    List<T> saveAll(List<T> entities);
}
