package com.example.crudapp.utils;

import java.util.List;

public interface GenericDAO <T, K> {

    void save(T entity);
    void delete(K key);
    void update(T entity);
    T findOne(K key);
    List<T> findAll();


}
