package com.example.david.remindmeat.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.david.remindmeat.model.User;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    public Maybe<List<User>> select();

    @Query("SELECT * FROM users WHERE email=:email")
    public Maybe<User> selectByEmail(String email);

    @Insert
    public void insert(User user);

    @Update
    public void update(User user);

    @Delete
    public void delete(User user);
}
