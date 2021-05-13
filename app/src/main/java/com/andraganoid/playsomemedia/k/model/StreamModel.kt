package com.andraganoid.playsomemedia.k.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andraganoid.playsomemedia.k.util.STREAM_TABLE

@Entity(tableName = STREAM_TABLE)
data class StreamModel(
    @PrimaryKey
    private var url: String? = null,

    @ColumnInfo
    private var name: String? = null
)
