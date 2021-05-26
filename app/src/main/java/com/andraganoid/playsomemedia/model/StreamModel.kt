package com.andraganoid.playsomemedia.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andraganoid.playsomemedia.util.STREAM_TABLE

@Entity(tableName = STREAM_TABLE)
data class StreamModel(
    @PrimaryKey
    var url: String = "",

    @ColumnInfo
    var name: String? = null
)
