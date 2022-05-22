package com.lpirro.cryptomovies.data.repository

import com.lpirro.cryptomovies.domain.model.Movie
import com.lpirro.cryptomovies.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface CryptoMoviesRepository {
    suspend fun getPopularMovies(): Flow<List<Movie>>
    suspend fun getTopRatedMovies(): Flow<List<Movie>>
    suspend fun getNowPlayingMovies(): Flow<List<Movie>>
    suspend fun getUpcomingMovies(): Flow<List<Movie>>
    suspend fun getMovie(movieId: Long): Flow<Movie>
    suspend fun getMovieDetail(movieId: Long): Flow<MovieDetail>
}
