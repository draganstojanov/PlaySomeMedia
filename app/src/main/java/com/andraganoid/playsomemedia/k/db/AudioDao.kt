package com.andraganoid.playsomemedia.k.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andraganoid.playsomemedia.model.Audio

@Dao
interface AudioDao {
    @Query("SELECT * FROM AUDIO_TABLE ORDER BY artist AND title ASC")
    fun getAllAudios(): LiveData<List<Audio?>?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(audio: Audio?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAudioList(audioList:List<Audio?>?)

    @Query("DELETE FROM AUDIO_TABLE")
    fun deleteAllAudios()

    @Delete
    fun deleteAudio(audio: Audio?)
}