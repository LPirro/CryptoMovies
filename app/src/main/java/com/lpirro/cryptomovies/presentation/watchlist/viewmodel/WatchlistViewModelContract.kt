package com.lpirro.cryptomovies.presentation.watchlist.viewmodel

import kotlinx.coroutines.Job

interface WatchlistViewModelContract {
    fun fetchWatchlist(): Job
}
