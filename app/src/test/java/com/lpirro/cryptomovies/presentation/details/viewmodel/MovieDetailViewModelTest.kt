package com.lpirro.cryptomovies.presentation.details.viewmodel

import app.cash.turbine.test
import com.lpirro.cryptomovies.domain.usecases.GetMovieDetailUseCase
import com.lpirro.cryptomovies.presentation.details.viewmodel.MovieDetailViewModel.MovieDetailUiState
import com.lpirro.cryptomovies.util.MockUtil
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class MovieDetailViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var viewModel: MovieDetailViewModel

    private var useCase: GetMovieDetailUseCase = mock()

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = MovieDetailViewModel(useCase)
    }

    @Test
    fun fetchMovieDetailHeaderSuccessTest() = runTest {
        val mockMovieId = 9435L
        val movieMock = MockUtil.mockMovieList()[0]
        whenever(useCase.getMovie(mockMovieId)).thenReturn(movieMock)

        viewModel.fetchMovieHeader(mockMovieId)
        viewModel.movie.test {
            val emission = awaitItem() as MovieDetailUiState.HeaderSuccess
            assertEquals(emission.movie, movieMock)
        }
    }

    @Test
    fun fetchMovieDetailHeaderErrorTest() = runTest {
        val mockMovieId = 9435L
        whenever(useCase.getMovie(mockMovieId)).thenReturn(null)

        viewModel.fetchMovieHeader(mockMovieId)
        viewModel.movie.test {
            val emission = awaitItem()
            assertEquals(emission, MovieDetailUiState.Error)
        }
    }

    @Test
    fun fetchMovieDetailDetailsSuccessTest() = runTest {
        val mockMovieId = 9435L
        val movieDetailsMock = MockUtil.mockMovieDetail()
        whenever(useCase.getMovieDetail(mockMovieId)).thenReturn(movieDetailsMock)

        viewModel.fetchMovieDetail(mockMovieId)
        viewModel.movie.test {
            val emission = awaitItem() as MovieDetailUiState.DetailSuccess
            assertEquals(emission.movie, movieDetailsMock)
        }
    }

    @Test
    fun fetchMovieDetailDetailsErrorTest() = runTest {
        val mockMovieId = 9435L
        whenever(useCase.getMovieDetail(mockMovieId)).thenReturn(null)

        viewModel.fetchMovieDetail(mockMovieId)
        viewModel.movie.test {
            val emission = awaitItem()
            assertEquals(emission, MovieDetailUiState.Error)
        }
    }

    @Test
    fun isAlreadyOnWatchListReturnTrueTest() = runTest {
        val mockMovieId = 9435L
        whenever(useCase.isAlreadyOnWatchlist(mockMovieId)).thenReturn(true)

        viewModel.isAlreadyOnWatchlist(mockMovieId)
        viewModel.movie.test {
            val emission = awaitItem() as MovieDetailUiState.WatchListEvent
            assertEquals(emission.isAlreadyOnWatchList, true)
        }
    }

    @Test
    fun isAlreadyOnWatchListReturnFalseTest() = runTest {
        val mockMovieId = 9435L
        whenever(useCase.isAlreadyOnWatchlist(mockMovieId)).thenReturn(false)

        viewModel.isAlreadyOnWatchlist(mockMovieId)
        viewModel.movie.test {
            val emission = awaitItem() as MovieDetailUiState.WatchListEvent
            assertEquals(emission.isAlreadyOnWatchList, false)
        }
    }
}
