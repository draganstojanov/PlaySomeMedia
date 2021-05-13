package com.andraganoid.playsomemedia.k.di

import android.app.Application
import androidx.room.Room
import com.andraganoid.playsomemedia.k.db.AudioDao
import com.andraganoid.playsomemedia.k.db.PlaySomeDatabase
import com.andraganoid.playsomemedia.k.db.StreamDao
import com.andraganoid.playsomemedia.k.db.VideoDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object Modules {

    private val viewModelModule = module {
//        viewModel { CountriesViewModel(get()) }
//        viewModel { BordersViewModel(get()) }
    }

    private val singleModule = module {
//        single { CountriesRepository(get(), get()) }
//        single { Preferences(androidContext()) }
    }


    private val databaseModule = module {

        fun database(application: Application): PlaySomeDatabase {
            return Room.databaseBuilder(application, PlaySomeDatabase::class.java, "play-database")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun videoDao(database: PlaySomeDatabase): VideoDao {
            return database.videoDao()
        }

        fun audioDao(database: PlaySomeDatabase): AudioDao {
            return database.audioDao()
        }

        fun streamDao(database: PlaySomeDatabase): StreamDao {
            return database.streamDao()
        }

        single { database(androidApplication()) }
        single { videoDao(get()) }
        single { audioDao(get()) }
        single { streamDao(get()) }
    }


    val appModule =
        listOf(
            viewModelModule,
            singleModule,
            databaseModule
        )


}