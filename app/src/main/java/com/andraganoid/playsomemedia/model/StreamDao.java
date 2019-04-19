package com.andraganoid.playsomemedia.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StreamDao {

    @Query("SELECT * FROM stream_table ORDER BY name ASC")
    LiveData <List <Stream>> getAllStreams();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Stream stream);

    @Query("DELETE FROM stream_table")
    void deleteAllStreams();

    @Delete
    void deleteStream(Stream stream);

}
