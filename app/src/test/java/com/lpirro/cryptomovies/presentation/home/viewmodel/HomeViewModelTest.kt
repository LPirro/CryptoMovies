package com.lpirro.cryptomovies.presentation.home.viewmodel

import app.cash.turbine.test
import com.lpirro.cryptomovies.domain.usecases.GetHomeScreenUseCase
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
class HomeViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var viewModel: HomeViewModel

    private var useCase: GetHomeScreenUseCase = mock()

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun fetchMovieHomeScreenWithSuccessTest() = runTest {
        val mockHomeScreenData = MockUtil.mockHomeScreen()
        whenever(useCase.getHomeScreen()).thenReturn(mockHomeScreenData)
        viewModel.fetchHomeScreen()

        viewModel.homeScreen.test {
            val emission = awaitItem() as HomeViewModel.HomeUiState.Success
            assertEquals(emission.homeScreen.popularMovies, mockHomeScreenData.popularMovies)
            assertEquals(emission.homeScreen.topRatedMovies, mockHomeScreenData.topRatedMovies)
            assertEquals(emission.homeScreen.nowPlayingMovies, mockHomeScreenData.nowPlayingMovies)
            assertEquals(emission.homeScreen.upcomingMovies, mockHomeScreenData.upcomingMovies)
        }
    }

    @Test
    fun fetchMovieHomeScreenWithErrorTest() = runTest {
        whenever(useCase.getHomeScreen()).thenReturn(null)
        viewModel.fetchHomeScreen()

        viewModel.homeScreen.test {
            val emission = awaitItem()
            assertEquals(emission, HomeViewModel.HomeUiState.Error)
        }
    }
}
