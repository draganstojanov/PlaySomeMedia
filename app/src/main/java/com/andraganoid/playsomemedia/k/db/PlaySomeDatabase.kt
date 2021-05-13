package com.andraganoid.playsomemedia.k.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andraganoid.playsomemedia.model.Audio
import com.andraganoid.playsomemedia.model.Stream
import com.andraganoid.playsomemedia.model.Video



@Database(entities = [Audio::class, Video::class, Stream::class], version = 2)

abstract class PlaySomeDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao
    abstract fun audioDao(): AudioDao
    abstract fun streamDao(): StreamDao
}