package com.ibarra.news.di

import androidx.room.Room
import com.ibarra.location.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val RoomTestModule = module {
    single {
        // In-Memory database config
        Room.inMemoryDatabaseBuilder(androidContext(), AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
    single { get<AppDatabase>().getPlaceDao() }
}