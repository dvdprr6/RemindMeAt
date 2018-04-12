package com.example.david.remindmeat.global;

import com.example.david.remindmeat.model.UserItem;

public class SharedObject {
    private static SharedObject instance;

    private UserItem userItem;

    SharedObject(){}

    public static synchronized SharedObject getInstance(){
        if(instance == null){
            instance = new SharedObject();
        }

        return instance;
    }

    public void setSharedUserItemObject(UserItem userItem){
        this.userItem = userItem;
    }

    public UserItem getSharedUserItemObject(){
        return userItem;
    }
}
