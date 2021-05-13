package com.andraganoid.playsomemedia.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.concurrent.TimeUnit;

//@Entity(tableName = "audio_table")
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
        this.artist = artist;
        this.duration = duration;
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

    public String getArtist() {
        return artist;
    }

    public long getDuration() {
        return duration;
    }

    public String getFormattedDuration() {

        return String.format("%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(getDuration()),
                TimeUnit.MILLISECONDS.toSeconds(getDuration()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getDuration()))
        );
    }

    public String getFormattedTitle() {
        StringBuilder sb = new StringBuilder();
        if (!getArtist().isEmpty() && !getArtist().contains("<unknown>")) {
            sb.append(getArtist())
                    .append(" - ");
        }
        sb.append(getTitle());
        return sb.toString();
    }
}
