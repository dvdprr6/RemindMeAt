package com.example.david.remindmeat.model;

import android.content.ContentValues;
import android.os.Parcelable;

public abstract class Item implements Parcelable {
    public abstract void setId(String id);
    public abstract String getId();
    public abstract ContentValues toValues();
}
