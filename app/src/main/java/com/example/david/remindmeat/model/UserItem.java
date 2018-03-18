package com.example.david.remindmeat.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;


public class UserItem implements Parcelable {

    private static final int USER_ITEM_COLUMNS = 5;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserItem(String userId, String firstName, String lastName, String email, String password){

        if(userId == null){
            userId = UUID.randomUUID().toString();
        }

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    UserItem(Parcel in) {
        this.userId = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.password = in.readString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public ContentValues toValues(){
        ContentValues values = new ContentValues(USER_ITEM_COLUMNS);

        return values;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<UserItem> CREATOR = new Parcelable.Creator<UserItem>() {
        @Override
        public UserItem createFromParcel(Parcel source) {
            return new UserItem(source);
        }

        @Override
        public UserItem[] newArray(int size) {
            return new UserItem[size];
        }
    };
}
