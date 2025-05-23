package com.be.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    void save(T entity);

    List<T> findAll();

    T findById(ID id);

    void update(T entity);

    void delete(T entity);
}
