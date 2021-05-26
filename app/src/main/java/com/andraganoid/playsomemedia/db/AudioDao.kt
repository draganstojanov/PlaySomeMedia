package com.andraganoid.playsomemedia.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andraganoid.playsomemedia.model.AudioModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AudioDao {
    @Query("SELECT * FROM AUDIO_TABLE ORDER BY artist AND title ASC")
    fun getAllAudios(): Flow<List<AudioModel?>>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insert(audio: AudioModel?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAudioList(audioList: List<AudioModel?>)

    @Query("DELETE FROM AUDIO_TABLE")
    fun deleteAllAudios()

//    @Delete
//    fun deleteAudio(audio: AudioModel?)
}