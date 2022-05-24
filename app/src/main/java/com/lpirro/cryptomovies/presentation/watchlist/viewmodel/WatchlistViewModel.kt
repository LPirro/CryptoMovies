package com.lpirro.cryptomovies.presentation.watchlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lpirro.cryptomovies.domain.model.WatchlistMovie
import com.lpirro.cryptomovies.domain.usecases.GetWatchlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val watchlistUseCase: GetWatchlistUseCase
) : ViewModel(), WatchlistViewModelContract {

    private val _watchlist = MutableStateFlow<WatchlistUiState>(WatchlistUiState.Loading)
    val watchlist: StateFlow<WatchlistUiState> = _watchlist

    fun refresh() {
        fetchWatchlist()
    }

    override fun fetchWatchlist() = viewModelScope.launch {
        try {
            val watchlist = watchlistUseCase.getWatchlistMovies()
            if (watchlist.isEmpty()) {
                _watchlist.value = WatchlistUiState.Empty
            } else {
                _watchlist.value = WatchlistUiState.Success(watchlist)
            }
        } catch (e: Exception) {
            _watchlist.value = WatchlistUiState.Error(e.message ?: "Error")
        }
    }

    sealed class WatchlistUiState {
        object Loading : WatchlistUiState()
        data class Success(val watchlist: List<WatchlistMovie>) : WatchlistUiState()
        object Empty : WatchlistUiState()
        data class Error(val error: String) : WatchlistUiState()
    }
}
