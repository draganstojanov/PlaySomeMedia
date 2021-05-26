package com.andraganoid.playsomemedia.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andraganoid.playsomemedia.model.VideoModel

@Dao
interface VideoDao {
    @Query("SELECT * FROM video_table ORDER BY dateTaken ASC")
    fun getAllVideos(): LiveData<List<VideoModel?>?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(video: VideoModel?)

    @Query("DELETE FROM video_table")
    fun deleteAllVideos()

    @Delete
    fun deleteVideo(video: VideoModel?)
}