package com.ibarra.location.di

import com.ibarra.location.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val RoomModule = module {
    single { AppDatabase.getInstance(androidApplication().applicationContext) }
    single { get<AppDatabase>().getPlaceDao() }
}