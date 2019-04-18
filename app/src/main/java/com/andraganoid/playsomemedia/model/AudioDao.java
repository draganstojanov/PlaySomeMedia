package com.andraganoid.playsomemedia.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AudioDao {

    @Query("SELECT * FROM audio_table ORDER BY artist ASC")
    LiveData <List <Audio>> getAllAudios();

    @Insert
    void insert(Audio audio);

    @Query("DELETE FROM audio_table")
    void deleteAllAudio();


}
