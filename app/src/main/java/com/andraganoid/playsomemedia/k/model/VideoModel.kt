package com.andraganoid.playsomemedia.k.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andraganoid.playsomemedia.k.util.VIDEO_TABLE

@Entity(tableName = VIDEO_TABLE)
data class VideoModel(
    @PrimaryKey
    private var data: String? = null,

    @ColumnInfo
    private val displayName: String? = null,

    @ColumnInfo
    private val title: String? = null,

    @ColumnInfo
    private val dateTaken: Long = 0,

    @ColumnInfo
    private val duration: Long = 0,

    @ColumnInfo
    private val width: Int = 0,

    @ColumnInfo
    private val height: Int = 0,

    @ColumnInfo
    private var resolution: String? = null
)
