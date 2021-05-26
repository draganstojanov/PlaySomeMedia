package com.andraganoid.playsomemedia.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andraganoid.playsomemedia.util.AUDIO_TABLE
import org.jetbrains.annotations.NotNull


@Entity(tableName = AUDIO_TABLE)
data class AudioModel(
    @PrimaryKey
    @NotNull
    var data: String = "",

    @ColumnInfo
    val displayName: String? = null,

    @ColumnInfo
    val title: String? = null,

    @ColumnInfo
    val artist: String? = null,

    @ColumnInfo
    var duration: Long? = 0
)
