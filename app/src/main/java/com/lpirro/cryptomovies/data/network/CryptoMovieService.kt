package com.lpirro.cryptomovies.data.network

import com.lpirro.cryptomovies.data.network.model.MovieDetailsDto
import com.lpirro.cryptomovies.data.network.model.MoviesListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoMovieService {
    @GET("movie/popular")
    suspend fun fetchPopularMovies(): MoviesListDto

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovies(): MoviesListDto

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(): MoviesListDto

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(): MoviesListDto

    @GET("movie/{movieId}")
    suspend fun fetchMovieDetail(
        @Path("movieId") movieId: Long,
        @Query("append_to_response") appendToResponse: String
    ): MovieDetailsDto
}
