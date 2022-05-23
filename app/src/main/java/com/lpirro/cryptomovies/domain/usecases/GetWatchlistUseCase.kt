package com.lpirro.cryptomovies.domain.usecases

import com.lpirro.cryptomovies.domain.model.WatchlistMovie

interface GetWatchlistUseCase {
    suspend fun getWatchlistMovies(): List<WatchlistMovie>
}
