package com.lpirro.cryptomovies.di

import com.lpirro.cryptomovies.data.network.CryptoMovieService
import com.lpirro.cryptomovies.data.peristance.MoviesDao
import com.lpirro.cryptomovies.data.repository.CryptoMoviesRepository
import com.lpirro.cryptomovies.data.repository.CryptoMoviesRepositoryImpl
import com.lpirro.cryptomovies.data.repository.mapper.MovieDetailMapper
import com.lpirro.cryptomovies.data.repository.mapper.MovieDetailMapperImpl
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
        movieMapper: MovieMapper,
        movieDetailMapper: MovieDetailMapper,
    ): CryptoMoviesRepository {
        return CryptoMoviesRepositoryImpl(
            cryptoMovieService,
            moviesDao,
            movieMapper,
            movieDetailMapper
        )
    }

    @Provides
    fun provideMovieMapper(): MovieMapper {
        return MovieMapperImpl()
    }

    @Provides
    fun provideMovieDetailsMapper(): MovieDetailMapper {
        return MovieDetailMapperImpl()
    }
}
