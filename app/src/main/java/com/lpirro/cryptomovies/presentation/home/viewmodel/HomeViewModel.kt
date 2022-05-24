package com.lpirro.cryptomovies.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lpirro.cryptomovies.domain.model.HomeScreen
import com.lpirro.cryptomovies.domain.usecases.GetHomeScreenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeScreenUseCase: GetHomeScreenUseCase
) : ViewModel(), HomeViewModelContract {

    private val _homeScreen = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeScreen: StateFlow<HomeUiState> = _homeScreen

    init {
        fetchHomeScreen()
    }

    fun retry() {
        fetchHomeScreen()
    }

    override fun fetchHomeScreen() = viewModelScope.launch {
        try {
            _homeScreen.value = HomeUiState.Loading
            val homeScreen = homeScreenUseCase.getHomeScreen()
            _homeScreen.value = HomeUiState.Success(homeScreen)
        } catch (e: Exception) {
            _homeScreen.value = HomeUiState.Error
        }
    }

    sealed class HomeUiState {
        object Loading : HomeUiState()
        data class Success(val homeScreen: HomeScreen) : HomeUiState()
        object Error : HomeUiState()
    }
}
