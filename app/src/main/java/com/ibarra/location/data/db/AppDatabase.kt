package com.ibarra.location.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ibarra.location.data.db.dao.PlaceDao
import com.ibarra.location.data.db.entity.Place


@Database(entities = arrayOf(Place::class), version = AppDatabase.DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPlaceDao(): PlaceDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "ibarra_location.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .build()
    }
}