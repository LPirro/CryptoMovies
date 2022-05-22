package com.lpirro.cryptomovies.presentation.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lpirro.cryptomovies.domain.model.Movie
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

    override fun fetchMovieDetail(movieId: Long) = viewModelScope.launch {
        try {
            val movie = movieDetailUseCase.getMovieDetail(movieId)
            _movie.value = MovieDetailUiState.Success(movie)
        } catch (e: Exception) {
            _movie.value =
                MovieDetailUiState.Error(e.message ?: "Error") // TODO REMOVE HARDCODED VALUE
        }
    }

    sealed class MovieDetailUiState {
        object Loading : MovieDetailUiState()
        data class Success(val movie: Movie) : MovieDetailUiState()
        data class Error(val error: String) : MovieDetailUiState()
    }
}
