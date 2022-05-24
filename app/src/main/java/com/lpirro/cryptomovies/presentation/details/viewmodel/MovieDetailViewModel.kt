package com.lpirro.cryptomovies.presentation.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lpirro.cryptomovies.domain.model.Movie
import com.lpirro.cryptomovies.domain.model.MovieDetail
import com.lpirro.cryptomovies.domain.usecases.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase
) : ViewModel(), MovieDetailViewModelContract {

    private val _movie = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Loading)
    val movie: StateFlow<MovieDetailUiState> = _movie

    fun start(movieId: Long) {
        fetchMovie(movieId)
        fetchMovieDetail(movieId)
        isAlreadyOnWatchlist(movieId)
    }

    override fun fetchMovie(movieId: Long) = viewModelScope.launch {
        try {
            val movie = movieDetailUseCase.getMovie(movieId)
            _movie.value = MovieDetailUiState.HeaderSuccess(movie)
        } catch (e: Exception) {
            _movie.value =
                MovieDetailUiState.Error(e.message ?: "Error") // TODO REMOVE HARDCODED VALUE
        }
    }

    override fun fetchMovieDetail(movieId: Long) = viewModelScope.launch {
        try {
            val movie = movieDetailUseCase.getMovieDetail(movieId)
            _movie.value = MovieDetailUiState.DetailSuccess(movie)
        } catch (e: Exception) {
            _movie.value =
                MovieDetailUiState.Error(e.message ?: "Error") // TODO REMOVE HARDCODED VALUE
        }
    }

    override fun addToWatchlist(movieId: Long) = viewModelScope.launch {
        val isAlreadyOnWatchList = movieDetailUseCase.isAlreadyOnWatchlist(movieId)
        _movie.value = MovieDetailUiState.WatchListEvent(!isAlreadyOnWatchList)
        if (isAlreadyOnWatchList) {
            movieDetailUseCase.removeFromWatchlist(movieId)
        } else {
            movieDetailUseCase.addToWatchlist(movieId)
        }
    }

    override fun isAlreadyOnWatchlist(movieId: Long) = viewModelScope.launch {
        val isAlreadyOnWatchList = movieDetailUseCase.isAlreadyOnWatchlist(movieId)
        _movie.value = MovieDetailUiState.WatchListEvent(isAlreadyOnWatchList)
    }

    sealed class MovieDetailUiState {
        object Loading : MovieDetailUiState()
        data class HeaderSuccess(val movie: Movie) : MovieDetailUiState()
        data class DetailSuccess(val movie: MovieDetail) : MovieDetailUiState()
        data class WatchListEvent(val isAlreadyOnWatchList: Boolean) : MovieDetailUiState()
        data class Error(val error: String) : MovieDetailUiState()
    }
}
