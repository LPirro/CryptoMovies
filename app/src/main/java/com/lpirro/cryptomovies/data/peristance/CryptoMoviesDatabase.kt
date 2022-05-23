package com.lpirro.cryptomovies.data.peristance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lpirro.cryptomovies.domain.model.Movie
import com.lpirro.cryptomovies.domain.model.WatchlistMovie

@Database(entities = [Movie::class, WatchlistMovie::class], version = 1, exportSchema = true)
abstract class CryptoMoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
