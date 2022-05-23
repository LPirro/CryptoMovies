package com.lpirro.cryptomovies.domain.usecases

import com.lpirro.cryptomovies.data.repository.CryptoMoviesRepository
import com.lpirro.cryptomovies.domain.model.WatchlistMovie
import kotlinx.coroutines.flow.single

class GetWatchlistUseCaseImpl(
    val repository: CryptoMoviesRepository
) : GetWatchlistUseCase {
    override suspend fun getWatchlistMovies(): List<WatchlistMovie> {
        return repository.getWatchlist().single()
    }
}
