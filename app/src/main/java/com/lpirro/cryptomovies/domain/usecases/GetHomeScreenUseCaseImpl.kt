package com.lpirro.cryptomovies.domain.usecases

import com.lpirro.cryptomovies.data.repository.CryptoMoviesRepository
import com.lpirro.cryptomovies.domain.model.HomeScreen
import kotlinx.coroutines.flow.single

class GetHomeScreenUseCaseImpl(
    private val repository: CryptoMoviesRepository,
) : GetHomeScreenUseCase {
    override suspend fun getHomeScreen(): HomeScreen {
        return HomeScreen(
            upcomingMovies = repository.getUpcomingMovies().single(),
            nowPlayingMovies = repository.getNowPlayingMovies().single(),
            topRatedMovies = repository.getTopRatedMovies().single(),
            popularMovies = repository.getPopularMovies().single()
        )
    }
}
