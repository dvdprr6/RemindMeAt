package com.example.david.remindmeat.dao.service;

import android.content.Context;

import com.example.david.remindmeat.database.AppDatabase;
import com.example.david.remindmeat.model.User;

import java.util.List;

public class UserServceImplementation implements UserService {
    private AppDatabase appDatabase;

    public UserServceImplementation(Context context){
        appDatabase = AppDatabase.getInstance(context);
    }

    @Override
    public List<User> findAllUsers(User user) {
        return appDatabase.getUserDao().select();
    }

    @Override
    public User findUserById(String userId) {
        return appDatabase.getUserDao().selectById(userId);
    }

    @Override
    public void createUser(User user) {
        appDatabase.getUserDao().insert(user);
    }

    @Override
    public void updateUser(User user) {
        appDatabase.getUserDao().update(user);
    }

    @Override
    public void deletUser(User user) {
        appDatabase.getUserDao().delete(user);
    }
}
