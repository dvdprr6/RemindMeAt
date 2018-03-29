package com.example.david.remindmeat.dao.service;

import com.example.david.remindmeat.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUsers(User user);
    public User findUserById(String userId);
    public void createUser(User user);
    public void updateUser(User user);
    public void deletUser(User user);
}
