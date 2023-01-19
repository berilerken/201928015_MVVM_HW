package com.example.a201928015_mvvm_hw.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;


import com.google.gson.annotations.SerializedName;

@Entity(tableName = "catimg",indices = @Index(value = {"id"},unique = true))
public class Model {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    String id;

    @SerializedName("url")
    @ColumnInfo(name = "image")
    String url;

    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
