package com.lpirro.cryptomovies.data.network

import com.lpirro.cryptomovies.data.network.model.MoviesListDto
import retrofit2.http.GET

interface CryptoMovieService {
    @GET("movie/popular")
    suspend fun fetchPopularMovies(): MoviesListDto

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovies(): MoviesListDto

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(): MoviesListDto

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(): MoviesListDto
}
