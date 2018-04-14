package com.example.david.remindmeat.dao;

import com.example.david.remindmeat.model.Item;
import com.example.david.remindmeat.model.RemindItem;

import java.util.List;

public abstract class RemindDao implements Dao<RemindItem> {
    public abstract List<Item> findRemindItemsByUserId(String userId);
}
