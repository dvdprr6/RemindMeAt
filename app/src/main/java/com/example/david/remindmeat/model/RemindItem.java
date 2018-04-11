package com.example.david.remindmeat.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.david.remindmeat.table.RemindTable;

import java.util.UUID;

public class RemindItem implements Parcelable{
    private String remindId;
    private String description;
    private double longitude;
    private double latitude;
    private String userId;

    public RemindItem(){}

    public String getRemindId() {
        return remindId;
    }

    public void setRemindId(String remindId) {
        if(remindId == null){
            remindId = UUID.randomUUID().toString();
        }

        this.remindId = remindId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ContentValues toValues(){
        ContentValues values = new ContentValues(5);

        values.put(RemindTable.COLUMN_ID, this.remindId);
        values.put(RemindTable.COLUMN_DESCRIPTION, this.description);
        values.put(RemindTable.COLUMN_LONGITUDE, this.longitude);
        values.put(RemindTable.COLUMN_LATITUDE, this.latitude);
        values.put(RemindTable.COLUMN_REF_USER_ID, this.userId);

        return values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.remindId);
        dest.writeString(this.description);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeString(this.userId);
    }

    protected RemindItem(Parcel in){
        this.remindId = in.readString();
        this.description = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.userId = in.readString();
    }

    public static final Parcelable.Creator<RemindItem> CREATOR = new Parcelable.Creator<RemindItem>(){
        @Override
        public RemindItem createFromParcel(Parcel source){
            return new RemindItem(source);
        }

        @Override
        public RemindItem[] newArray(int size){
            return new RemindItem[size];
        }
    };
}
