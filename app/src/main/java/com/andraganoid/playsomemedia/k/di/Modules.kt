package com.andraganoid.playsomemedia.k.di

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

        //  single { Gson() }


    }

    val appModule =
        listOf(
            viewModelModule,
            singleModule,
            databaseModule
        )


}