package com.andraganoid.playsomemedia.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.concurrent.TimeUnit;

//@Entity(tableName = "video_table")
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

    public String getDisplayName() {
        return displayName;
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

    public long getDuration() {
        return duration;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getResolution() {
        return resolution;
    }

    public String getFormattedDuration() {

      return  String.format("%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(getDuration()),
                TimeUnit.MILLISECONDS.toSeconds(getDuration()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getDuration()))
        );
    }

}
