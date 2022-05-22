package com.lpirro.cryptomovies.di

import com.lpirro.cryptomovies.data.repository.CryptoMoviesRepository
import com.lpirro.cryptomovies.domain.usecases.GetHomeScreenUseCase
import com.lpirro.cryptomovies.domain.usecases.GetHomeScreenUseCaseImpl
import com.lpirro.cryptomovies.domain.usecases.GetMovieDetailUseCase
import com.lpirro.cryptomovies.domain.usecases.GetMovieDetailUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetHomeScreenUseCase(repository: CryptoMoviesRepository): GetHomeScreenUseCase {
        return GetHomeScreenUseCaseImpl(repository)
    }

    @Provides
    fun provideGetMovieDetailUseCase(repository: CryptoMoviesRepository): GetMovieDetailUseCase {
        return GetMovieDetailUseCaseImpl(repository)
    }
}
