package com.lpirro.cryptomovies.presentation.home.viewmodel

import kotlinx.coroutines.Job

interface HomeViewModelContract {
    fun fetchHomeScreen(): Job
}
