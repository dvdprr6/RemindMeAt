package com.example.david.remindmeat.dao;

import com.example.david.remindmeat.model.UserItem;

public abstract class UserDao implements Dao<UserItem> {
    public abstract boolean isUser(String email, String password);
    public abstract UserItem searchUserByEmail(String email);
}
