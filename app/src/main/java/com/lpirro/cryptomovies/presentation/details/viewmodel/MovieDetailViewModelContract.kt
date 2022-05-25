package com.lpirro.cryptomovies.presentation.details.viewmodel

import kotlinx.coroutines.Job

interface MovieDetailViewModelContract {
    fun fetchMovieHeader(movieId: Long): Job
    fun fetchMovieDetail(movieId: Long): Job
    fun addToWatchlist(movieId: Long): Job
    fun isAlreadyOnWatchlist(movieId: Long): Job
}
