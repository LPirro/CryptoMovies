package com.lpirro.cryptomovies.presentation.details.viewmodel

import kotlinx.coroutines.Job

interface MovieDetailViewModelContract {
    fun fetchMovieDetail(movieId: Long): Job
}
