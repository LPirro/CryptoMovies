package com.lpirro.cryptomovies.presentation.watchlist.viewmodel

import app.cash.turbine.test
import com.lpirro.cryptomovies.domain.usecases.GetWatchlistUseCase
import com.lpirro.cryptomovies.presentation.watchlist.viewmodel.WatchlistViewModel.WatchlistUiState
import com.lpirro.cryptomovies.util.MockUtil
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class WatchlistViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var viewModel: WatchlistViewModel

    private var useCase: GetWatchlistUseCase = mock()

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = WatchlistViewModel(useCase)
    }

    @Test
    fun fetchWatchlistSuccess() = runTest {
        val mockedWatchListData = MockUtil.mockWatchlistMovies()
        whenever(useCase.getWatchlistMovies()).thenReturn(mockedWatchListData)

        viewModel.fetchWatchlist()
        viewModel.watchlist.test {
            val emission = awaitItem() as WatchlistUiState.Success
            assertEquals(emission.watchlist, mockedWatchListData)
        }
    }

    @Test
    fun fetchWatchlistError() = runTest {
        whenever(useCase.getWatchlistMovies()).thenReturn(null)

        viewModel.fetchWatchlist()
        viewModel.watchlist.test {
            val emission = awaitItem()
            assertEquals(emission, WatchlistUiState.Error)
        }
    }
}
