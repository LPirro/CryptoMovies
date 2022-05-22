package com.lpirro.cryptomovies.di

import android.app.Application
import androidx.room.Room
import com.lpirro.cryptomovies.data.peristance.CryptoMoviesDatabase
import com.lpirro.cryptomovies.data.peristance.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): CryptoMoviesDatabase {
        return Room
            .databaseBuilder(application, CryptoMoviesDatabase::class.java, "cryptomovies.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(cryptoMoviesDatabase: CryptoMoviesDatabase): MoviesDao {
        return cryptoMoviesDatabase.moviesDao()
    }
}
