package com.lpirro.cryptomovies.data.repository

import com.lpirro.cryptomovies.data.network.CryptoMovieService
import com.lpirro.cryptomovies.data.network.interceptors.NetworkConnectionInterceptor
import com.lpirro.cryptomovies.data.network.interceptors.NetworkConnectionInterceptor.NoConnectivityException
import com.lpirro.cryptomovies.data.peristance.MoviesDao
import com.lpirro.cryptomovies.data.repository.mapper.MovieDetailMapper
import com.lpirro.cryptomovies.data.repository.mapper.MovieMapper
import com.lpirro.cryptomovies.domain.model.Category
import com.lpirro.cryptomovies.util.MockUtil
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class CryptoMoviesRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var repository: CryptoMoviesRepository

    private var moviesDao: MoviesDao = mock()
    private var networkConnectionInterceptor: NetworkConnectionInterceptor = mock()
    private var service: CryptoMovieService = mock()

    @Inject
    lateinit var movieMapper: MovieMapper

    @Inject
    lateinit var movieDetailMapper: MovieDetailMapper

    @Before
    fun setup() {
        hiltRule.inject()
        repository = CryptoMoviesRepositoryImpl(service, moviesDao, movieMapper, movieDetailMapper)
    }

    @Test
    fun fetchPopularMoviesFromNetworkTest() = runTest {
        val mockData = MockUtil.mockMovieList().filter { it.category == Category.POPULAR }
        whenever(service.fetchPopularMovies()).thenReturn(MockUtil.mockMovieListDto())
        whenever(moviesDao.getMoviesListWithCategory(Category.POPULAR)).thenReturn(mockData)

        val result = repository.getPopularMovies().single()
        assertEquals(result.size, 1)
        assertEquals(result.first().title, "Spider-Man")
    }

    @Test
    fun fetchTopRatedMoviesFromNetworkTest() = runTest {
        val mockData = MockUtil.mockMovieList().filter { it.category == Category.TOP_RATED }
        whenever(service.fetchTopRatedMovies()).thenReturn(MockUtil.mockMovieListDto())
        whenever(moviesDao.getMoviesListWithCategory(Category.TOP_RATED)).thenReturn(mockData)

        val result = repository.getTopRatedMovies().single()
        assertEquals(result.size, 1)
        assertEquals(result.first().title, "Ironman")
    }

    @Test
    fun fetchNowPlayingMoviesFromNetworkTest() = runTest {
        val mockData = MockUtil.mockMovieList().filter { it.category == Category.NOW_PLAYING }
        whenever(service.fetchNowPlayingMovies()).thenReturn(MockUtil.mockMovieListDto())
        whenever(moviesDao.getMoviesListWithCategory(Category.NOW_PLAYING)).thenReturn(mockData)

        val result = repository.getNowPlayingMovies().single()
        assertEquals(result.size, 1)
        assertEquals(result.first().title, "The Tourist")
    }

    @Test
    fun fetchUpcomingMoviesFromNetworkTest() = runTest {
        val mockData = MockUtil.mockMovieList().filter { it.category == Category.UPCOMING }
        whenever(service.fetchUpcomingMovies()).thenReturn(MockUtil.mockMovieListDto())
        whenever(moviesDao.getMoviesListWithCategory(Category.UPCOMING)).thenReturn(mockData)

        val result = repository.getUpcomingMovies().single()
        assertEquals(result.size, 1)
        assertEquals(result.first().title, "Palla")
    }

    @Test
    fun fetchNowPlayingMoviesFromDatabaseTest() = runTest {
        val mockData = MockUtil.mockMovieList().filter { it.category == Category.NOW_PLAYING }
        whenever(service.fetchNowPlayingMovies()).thenReturn(MockUtil.mockMovieListDto())
        whenever(moviesDao.getMoviesListWithCategory(Category.NOW_PLAYING)).thenReturn(mockData)
        `when`(networkConnectionInterceptor.intercept(any())).thenThrow(NoConnectivityException())

        val expectedResult = repository.getNowPlayingMovies().single()
        val resultFromDatabase = moviesDao.getMoviesListWithCategory(Category.NOW_PLAYING)
        assertEquals(expectedResult, resultFromDatabase)
    }

    @Test
    fun fetchPopularMoviesFromDatabaseTest() = runTest {
        val mockData = MockUtil.mockMovieList().filter { it.category == Category.POPULAR }
        whenever(moviesDao.getMoviesListWithCategory(Category.POPULAR)).thenReturn(mockData)
        whenever(service.fetchPopularMovies()).thenReturn(MockUtil.mockMovieListDto())
        `when`(networkConnectionInterceptor.intercept(any())).thenThrow(NoConnectivityException())

        val expectedResult = repository.getPopularMovies().single()
        val resultFromDatabase = moviesDao.getMoviesListWithCategory(Category.POPULAR)
        assertEquals(expectedResult, resultFromDatabase)
    }

    @Test
    fun fetchTopRatedMoviesFromDatabaseTest() = runTest {
        val mockData = MockUtil.mockMovieList().filter { it.category == Category.TOP_RATED }
        whenever(moviesDao.getMoviesListWithCategory(Category.TOP_RATED)).thenReturn(mockData)
        whenever(service.fetchTopRatedMovies()).thenReturn(MockUtil.mockMovieListDto())
        `when`(networkConnectionInterceptor.intercept(any())).thenThrow(NoConnectivityException())

        val expectedResult = repository.getTopRatedMovies().single()
        val resultFromDatabase = moviesDao.getMoviesListWithCategory(Category.TOP_RATED)
        assertEquals(expectedResult, resultFromDatabase)
    }

    @Test
    fun fetchUpcomingMoviesFromDatabaseTest() = runTest {
        val mockData = MockUtil.mockMovieList().filter { it.category == Category.UPCOMING }
        whenever(moviesDao.getMoviesListWithCategory(Category.UPCOMING)).thenReturn(mockData)
        whenever(service.fetchUpcomingMovies()).thenReturn(MockUtil.mockMovieListDto())
        `when`(networkConnectionInterceptor.intercept(any())).thenThrow(NoConnectivityException())

        val expectedResult = repository.getUpcomingMovies().single()
        val resultFromDatabase = moviesDao.getMoviesListWithCategory(Category.UPCOMING)
        assertEquals(expectedResult, resultFromDatabase)
    }
}
