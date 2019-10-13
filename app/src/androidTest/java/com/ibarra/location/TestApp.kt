package com.ibarra.location

import android.app.Application
import com.ibarra.location.di.NetworkModule
import com.ibarra.location.di.RoomModule
import com.ibarra.location.di.ViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestApp)
            modules(listOf(NetworkModule, RoomModule, ViewModule))
        }
    }
}