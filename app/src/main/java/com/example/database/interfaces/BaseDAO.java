package com.example.database.interfaces;

import java.util.List;

public interface BaseDAO<T,K> {
    //insert-create
    void insert(T t);
    //update
    void update(T t);
    //delete
    void delete(T t);
    //retrieve
    T find(K key);
    List<T> getAll();
    void closeConnection();
}
