package com.lpirro.cryptomovies.domain.usecases

import com.lpirro.cryptomovies.domain.model.Movie

interface GetMovieDetailUseCase {
    suspend fun getMovieDetail(movieId: Long): Movie
}
