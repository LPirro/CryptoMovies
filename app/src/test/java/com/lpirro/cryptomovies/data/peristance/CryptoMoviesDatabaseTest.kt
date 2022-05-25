package com.lpirro.cryptomovies.data.peristance

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
abstract class CryptoMoviesDatabaseTest {
    lateinit var database: CryptoMoviesDatabase

    @Before
    fun initDatabase() {
        database =
            Room.inMemoryDatabaseBuilder(getApplicationContext(), CryptoMoviesDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}
