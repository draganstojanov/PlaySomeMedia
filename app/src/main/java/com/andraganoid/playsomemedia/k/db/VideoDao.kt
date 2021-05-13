package com.andraganoid.playsomemedia.k.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andraganoid.playsomemedia.model.Video

@Dao
interface VideoDao {
    @Query("SELECT * FROM video_table ORDER BY dateTaken ASC")
    fun getAllVideos(): LiveData<List<Video?>?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(video: Video?)

    @Query("DELETE FROM video_table")
    fun deleteAllVideos()

    @Delete
    fun deleteVideo(video: Video?)
}