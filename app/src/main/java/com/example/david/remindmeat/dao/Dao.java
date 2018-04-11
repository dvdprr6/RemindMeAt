package com.example.david.remindmeat.dao;

import com.example.david.remindmeat.model.Item;

import java.util.List;

public interface Dao<ITEM extends Item> {
    public List<ITEM> select();
    public ITEM selectById(String id);
    public long insert(ITEM object);
    public void update(ITEM object);
    public void delete(ITEM object);
}
