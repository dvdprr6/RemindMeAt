package com.example.david.remindmeat.callback;

import com.example.david.remindmeat.model.User;

import java.util.List;

public interface UserCallback {
    public void onUserLoaded(List<User> users);
    public void onUserLoaded(User user);
}
