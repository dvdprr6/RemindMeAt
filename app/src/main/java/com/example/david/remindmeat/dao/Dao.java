package com.example.david.remindmeat.dao;

import android.os.Parcelable;

import java.util.List;

public interface Dao<ITEM extends Parcelable> {
    public List<ITEM> select();
    public ITEM selectById(String id);
    public long insert(ITEM object);
    public void update(ITEM object);
    public void delete(ITEM object);
}
