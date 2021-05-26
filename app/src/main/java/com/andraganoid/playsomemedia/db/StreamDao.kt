package com.andraganoid.playsomemedia.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andraganoid.playsomemedia.model.StreamModel

@Dao
interface StreamDao {
    @Query("SELECT * FROM stream_table ORDER BY name ASC")
    fun getAllStreams(): LiveData<List<StreamModel?>?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(stream: StreamModel?)

    @Query("DELETE FROM stream_table")
    fun deleteAllStreams()

    @Delete
    fun deleteStream(stream: StreamModel?)
}