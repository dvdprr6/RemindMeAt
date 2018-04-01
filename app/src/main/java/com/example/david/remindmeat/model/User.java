package com.example.david.remindmeat.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity(tableName = "users",
        indices = {@Index(value = {"email"}, unique = true)})
public class User {

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    @NonNull
    private String userId;

    @ColumnInfo(name = "first_name")
    @NonNull
    private String firstName;

    @ColumnInfo(name = "last_name")
    @NonNull
    private String lastName;

    @ColumnInfo(name = "email")
    @NonNull
    private String email;

    @ColumnInfo(name = "password")
    @NonNull
    private String password;

    public User(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (userId == null) {
            userId = UUID.randomUUID().toString();
        }

        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
