package com.andraganoid.playsomemedia.k.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andraganoid.playsomemedia.model.Stream

@Dao
interface StreamDao {
    @Query("SELECT * FROM stream_table ORDER BY name ASC")
    fun getAllStreams(): LiveData<List<Stream?>?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(stream: Stream?)

    @Query("DELETE FROM stream_table")
    fun deleteAllStreams()

    @Delete
    fun deleteStream(stream: Stream?)
}