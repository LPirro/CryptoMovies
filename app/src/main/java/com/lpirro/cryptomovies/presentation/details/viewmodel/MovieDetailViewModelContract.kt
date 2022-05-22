package com.lpirro.cryptomovies.presentation.details.viewmodel

import kotlinx.coroutines.Job

interface MovieDetailViewModelContract {
    fun fetchMovie(movieId: Long): Job
    fun fetchMovieDetail(movieId: Long): Job
}
