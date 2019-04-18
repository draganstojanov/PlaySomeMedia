package com.andraganoid.playsomemedia.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "audio_table")
public class Audio {

    @PrimaryKey
    @NonNull
    private String data;

    @ColumnInfo
    private String displayName;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String artist;

    @ColumnInfo
    private long duration;

    public Audio(String data, String displayName, String title, String artist, long duration) {
        this.data = data;
        this.displayName = displayName;
        this.title = title;
        this.artist=artist;
        this.duration = duration;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
