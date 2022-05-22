package com.lpirro.cryptomovies.domain.usecases

import com.lpirro.cryptomovies.domain.model.HomeScreen

interface GetHomeScreenUseCase {
    suspend fun getHomeScreen(): HomeScreen
}
