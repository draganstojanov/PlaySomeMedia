package com.andraganoid.playsomemedia.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "video_table")
public class Video {

    @PrimaryKey
    @NonNull
    private String data;

    @ColumnInfo
    private String displayName;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private long dateTaken;

    @ColumnInfo
    private long duration;

    @ColumnInfo
    private int width;

    @ColumnInfo
    private int height;

    @ColumnInfo
    private String resolution;


    public Video(String data, String displayName, String title, long dateTaken, long duration, int width, int height, String resolution) {
        this.data = data;
        this.displayName = displayName;
        this.title = title;
        this.dateTaken = dateTaken;
        this.duration = duration;
        this.width = width;
        this.height = height;
        this.resolution = resolution;
    }

    @NonNull
    public String getData() {
        return data;
    }

    public void setData(@NonNull String data) {
        this.data = data;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(long dateTaken) {
        this.dateTaken = dateTaken;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}
