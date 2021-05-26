package com.andraganoid.playsomemedia.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andraganoid.playsomemedia.model.AudioModel
import com.andraganoid.playsomemedia.model.StreamModel
import com.andraganoid.playsomemedia.model.VideoModel


@Database(entities = [AudioModel::class, VideoModel::class, StreamModel::class], version = 2)

abstract class PlaySomeDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao
    abstract fun audioDao(): AudioDao
    abstract fun streamDao(): StreamDao
}