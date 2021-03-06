package com.lpirro.cryptomovies.domain.usecases

import com.lpirro.cryptomovies.data.repository.CryptoMoviesRepository
import com.lpirro.cryptomovies.domain.model.Movie
import com.lpirro.cryptomovies.domain.model.MovieDetail
import kotlinx.coroutines.flow.single

class GetMovieDetailUseCaseImpl(
    private val repository: CryptoMoviesRepository
) : GetMovieDetailUseCase {

    override suspend fun getMovie(movieId: Long): Movie {
        return repository.getMovie(movieId).single()
    }

    override suspend fun getMovieDetail(movieId: Long): MovieDetail {
        return repository.getMovieDetail(movieId).single()
    }

    override suspend fun addToWatchlist(movieId: Long) {
        repository.addToWatchList(movieId)
    }

    override suspend fun isAlreadyOnWatchlist(movieId: Long): Boolean {
        return repository.isAlreadyOnWatchList(movieId).single()
    }

    override suspend fun removeFromWatchlist(movieId: Long) {
        repository.removeFromWatchlist(movieId)
    }
}
