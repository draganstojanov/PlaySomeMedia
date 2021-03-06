package com.andraganoid.playsomemedia.k.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andraganoid.playsomemedia.k.util.AUDIO_TABLE


@Entity(tableName = AUDIO_TABLE)
data class AudioModel(
    @PrimaryKey
    var data: String? = null,

    @ColumnInfo
    val displayName: String? = null,

    @ColumnInfo
    val title: String? = null,

    @ColumnInfo
    val artist: String? = null,

    @ColumnInfo
    var duration: Long = 0
)
