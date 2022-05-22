package com.lpirro.cryptomovies.di

import com.lpirro.cryptomovies.data.network.CryptoMovieService
import com.lpirro.cryptomovies.data.peristance.MoviesDao
import com.lpirro.cryptomovies.data.repository.CryptoMoviesRepository
import com.lpirro.cryptomovies.data.repository.CryptoMoviesRepositoryImpl
import com.lpirro.cryptomovies.data.repository.mapper.MovieMapper
import com.lpirro.cryptomovies.data.repository.mapper.MovieMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCryptoMoviesRepository(
        cryptoMovieService: CryptoMovieService,
        moviesDao: MoviesDao,
        movieMapper: MovieMapper
    ): CryptoMoviesRepository {
        return CryptoMoviesRepositoryImpl(cryptoMovieService, moviesDao, movieMapper)
    }

    @Provides
    fun provideMovieMapper(): MovieMapper {
        return MovieMapperImpl()
    }
}
