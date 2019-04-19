package com.andraganoid.playsomemedia.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stream_table")
public class Stream {

    @PrimaryKey
    @NonNull
    private String url;

    @ColumnInfo
    private String name;

    public Stream(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }
}
