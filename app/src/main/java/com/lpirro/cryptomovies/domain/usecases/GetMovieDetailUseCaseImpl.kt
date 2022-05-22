package com.lpirro.cryptomovies.domain.usecases

import com.lpirro.cryptomovies.data.repository.CryptoMoviesRepository
import com.lpirro.cryptomovies.domain.model.Movie
import kotlinx.coroutines.flow.single

class GetMovieDetailUseCaseImpl(
    private val repository: CryptoMoviesRepository
) : GetMovieDetailUseCase {

    override suspend fun getMovieDetail(movieId: Long): Movie {
        return repository.getMovieDetail(movieId).single()
    }
}
