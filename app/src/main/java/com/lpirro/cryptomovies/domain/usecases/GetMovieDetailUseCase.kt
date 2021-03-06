package com.lpirro.cryptomovies.domain.usecases

import com.lpirro.cryptomovies.domain.model.Movie
import com.lpirro.cryptomovies.domain.model.MovieDetail

interface GetMovieDetailUseCase {
    suspend fun getMovie(movieId: Long): Movie
    suspend fun getMovieDetail(movieId: Long): MovieDetail
    suspend fun addToWatchlist(movieId: Long)
    suspend fun isAlreadyOnWatchlist(movieId: Long): Boolean
    suspend fun removeFromWatchlist(movieId: Long)
}
