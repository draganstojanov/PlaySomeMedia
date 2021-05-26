package com.andraganoid.playsomemedia.repository

import android.content.Context
import com.andraganoid.playsomemedia.db.AudioDao
import com.andraganoid.playsomemedia.model.AudioModel
import kotlinx.coroutines.flow.Flow

class AudioRepository(private val audioDao: AudioDao, private val context: Context) {

    suspend fun getAllAudios(): Flow<List<AudioModel?>> = audioDao.getAllAudios()

    private fun saveAllAudios(allAudiosList: List<AudioModel?>) {
        audioDao.insertAudioList(allAudiosList)
    }


    fun getSomeAudio() {
////        val getAudio: MutableList<AudioModel> = ArrayList()
////        val audioCursor: Cursor? =
////            context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
////        try {
////            audioCursor?.moveToFirst()
////            do { getAudio.add(
////                    AudioModel(
////                        audioCursor?.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)),
////                        audioCursor?.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)),
////                        audioCursor?.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)),
////                        audioCursor?.getString(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),
////                        audioCursor?.getLong(audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
////                    )
////                )
////            } while (audioCursor?.moveToNext() == true)
////            audioCursor?.close()
////        } catch (e: Exception) {
////            e.printStackTrace()
////        }
////      saveAllAudios(getAudio)
//
//
//         val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
//
//         val projection = arrayOf(
//             MediaStore.Audio.Media.ARTIST,
//             MediaStore.Audio.Media.TITLE,
//             MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//             MediaStore.Audio.Media.DISPLAY_NAME,
//             MediaStore.Audio.Media.DURATION
//         )
//
//         cursor = this.managedQuery(
//             MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//             projection,
//             selection,
//             null,
//             null
//         )
//
//         val songs: MutableList<String> = ArrayList()
//         while (cursor.moveToNext()) {
//             songs.add(
//                 cursor.getString(0)
//                     .toString() + "||" + cursor.getString(1) + "||" + cursor.getString(2) + "||" + cursor.getString(3) + "||" + cursor.getString(
//                     4
//                 ) + "||" + cursor.getString(5)
//             )
//         }
//
////         val projection = arrayOf(media-database-columns-to-retrieve)
////         val selection = sql-where-clause-with-placeholder-variables
////         val selectionArgs = values-of-placeholder-variables
////         val sortOrder = sql-order-by-clause
//
//         applicationContext.contentResolver.query(
//             MediaStore.media-type.Media.EXTERNAL_CONTENT_URI,
//             projection,
//             selection,
//             selectionArgs,
//             sortOrder
//         )?.use { cursor ->
//             while (cursor.moveToNext()) {
//                 // Use an ID column from the projection to get
//                 // a URI representing the media item itself.
//             }
//         }

    }


}