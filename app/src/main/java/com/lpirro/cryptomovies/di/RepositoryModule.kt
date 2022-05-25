package com.lpirro.cryptomovies.di

import com.lpirro.cryptomovies.data.network.CryptoMovieService
import com.lpirro.cryptomovies.data.peristance.MoviesDao
import com.lpirro.cryptomovies.data.repository.CryptoMoviesRepository
import com.lpirro.cryptomovies.data.repository.CryptoMoviesRepositoryImpl
import com.lpirro.cryptomovies.data.repository.mapper.MovieDetailMapper
import com.lpirro.cryptomovies.data.repository.mapper.MovieDetailMapperImpl
import com.lpirro.cryptomovies.data.repository.mapper.MovieMapper
import com.lpirro.cryptomovies.data.repository.mapper.MovieMapperImpl
import com.lpirro.cryptomovies.data.repository.mapper.util.CurrencyFormatter
import com.lpirro.cryptomovies.data.repository.mapper.util.CurrencyFormatterImpl
import com.lpirro.cryptomovies.data.repository.mapper.util.DateFormatter
import com.lpirro.cryptomovies.data.repository.mapper.util.DateFormatterImpl
import com.lpirro.cryptomovies.data.repository.mapper.util.ImageUrlProvider
import com.lpirro.cryptomovies.data.repository.mapper.util.ImageUrlProviderImpl
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
    fun provideMovieMapper(
        imageUrlProvider: ImageUrlProvider,
        dateFormatter: DateFormatter
    ): MovieMapper {
        return MovieMapperImpl(imageUrlProvider, dateFormatter)
    }

    @Provides
    fun provideMovieDetailsMapper(
        imageUrlProvider: ImageUrlProvider,
        currencyFormatter: CurrencyFormatter
    ): MovieDetailMapper {
        return MovieDetailMapperImpl(imageUrlProvider, currencyFormatter)
    }

    @Provides
    fun provideImageUrlProvider(): ImageUrlProvider {
        return ImageUrlProviderImpl()
    }

    @Provides
    fun provideDateFormatter(): DateFormatter {
        return DateFormatterImpl()
    }

    @Provides
    fun provideCurrencyFormatter(): CurrencyFormatter {
        return CurrencyFormatterImpl()
    }
}
