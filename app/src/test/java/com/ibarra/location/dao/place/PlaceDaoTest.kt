package com.ibarra.location.dao.place

import android.content.Context
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import com.ibarra.location.data.db.AppDatabase
import com.ibarra.location.data.db.dao.PlaceDao
import com.ibarra.location.data.db.entity.Place
import com.ibarra.news.di.RoomTestModule
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1], manifest=Config.NONE)
class PlaceDaoTest: KoinTest {
    private val appDatabase: AppDatabase by inject()
    private val placeDao: PlaceDao by inject()

    @Before
    fun before() {
        val app: Context = InstrumentationRegistry.getInstrumentation().context
        startKoin {
            androidContext(app)
            modules(listOf(RoomTestModule))
        }
    }

    @After
    fun after() {
        appDatabase.close()
        stopKoin()
    }

    @Test
    fun testInsertPlace() {
        val place = Place.to(placeRepository, "restaurant")

        placeDao.insert(place)

        val requestPlace = placeDao.findById(placeRepository.id)

        Assert.assertEquals(place, requestPlace)
    }
}