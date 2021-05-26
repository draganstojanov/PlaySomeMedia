package com.andraganoid.playsomemedia.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andraganoid.playsomemedia.util.VIDEO_TABLE

@Entity(tableName = VIDEO_TABLE)
data class VideoModel(
    @PrimaryKey
   var data: String = "",

    @ColumnInfo
   val displayName: String? = null,

    @ColumnInfo
  val title: String? = null,

    @ColumnInfo
   val dateTaken: Long = 0,

    @ColumnInfo
    val duration: Long = 0,

    @ColumnInfo
    val width: Int = 0,

    @ColumnInfo
 val height: Int = 0,

    @ColumnInfo
    var resolution: String? = null
)
