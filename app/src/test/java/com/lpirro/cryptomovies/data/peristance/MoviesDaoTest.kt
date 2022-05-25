package com.lpirro.cryptomovies.data.peristance

import com.lpirro.cryptomovies.domain.model.Category
import com.lpirro.cryptomovies.util.MockUtil
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class MoviesDaoTest : CryptoMoviesDatabaseTest() {

    private lateinit var moviesDao: MoviesDao

    @Before
    fun setup() {
        moviesDao = database.moviesDao()
    }

    @Test
    fun insertAndLoadMoviesListTest() = runBlocking {
        val mockData = MockUtil.mockMovieList()
        moviesDao.insertMovieList(mockData)

        val resultsFromDatabase = moviesDao.getMoviesListWithCategory(Category.POPULAR)
        assertEquals(resultsFromDatabase.first().id, mockData.first().id)
    }

    @Test
    fun getMovieWillReturnMovieCorrectlyTest() = runBlocking {
        val mockData = MockUtil.mockMovieList()
        moviesDao.insertMovieList(mockData)

        val resultFromDatabase = moviesDao.getMovie(mockData.first().id)
        assertEquals(resultFromDatabase.id, mockData.first().id)
    }

    @Test
    fun insertAndLoadFromWatchlistTest() = runBlocking {
        val mockData = MockUtil.mockMovieList()
        moviesDao.insertMovieList(mockData)

        val watchlistMovie = mockData.first()
        moviesDao.insertToWatchlist(watchlistMovie.id)

        val resultsFromDatabase = moviesDao.getWatchlist()
        assertEquals(resultsFromDatabase.first().id, watchlistMovie.id)
    }

    @Test
    fun deleteFromWatchListTest() = runBlocking {
        val mockData = MockUtil.mockMovieList()
        moviesDao.insertMovieList(mockData)
        moviesDao.insertToWatchlist(mockData.first().id)

        moviesDao.deleteFromWatchlist(mockData.first().id)
        val watchListMoviesOnDb = moviesDao.getWatchlist()
        assertEquals(watchListMoviesOnDb.size, 0)
    }

    @Test
    fun isAlreadyOnWatchListWillReturnTrue() = runBlocking {
        val mockData = MockUtil.mockMovieList()
        moviesDao.insertMovieList(mockData)
        moviesDao.insertToWatchlist(mockData.first().id)

        val isAlreadyOnWatchlist = moviesDao.isAlreadyOnWatchList(mockData.first().id)
        assertEquals(isAlreadyOnWatchlist, true)
    }

    @Test
    fun isAlreadyOnWatchListWillReturnFalse() = runBlocking {
        val mockData = MockUtil.mockMovieList()
        moviesDao.insertMovieList(mockData)
        moviesDao.insertToWatchlist(mockData[1].id)

        val isAlreadyOnWatchlist = moviesDao.isAlreadyOnWatchList(mockData.first().id)
        assertEquals(isAlreadyOnWatchlist, false)
    }
}
