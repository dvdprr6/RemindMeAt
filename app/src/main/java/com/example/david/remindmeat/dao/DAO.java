package com.example.david.remindmeat.dao;

import java.util.List;

public interface DAO<T> {
    public List<T> select();
    public T selectById(int id);
    public void insert(T object);
    public void update(T object);
    public void delete(T object);

}
