package com.lpirro.cryptomovies.data.peristance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lpirro.cryptomovies.domain.model.Category
import com.lpirro.cryptomovies.domain.model.Movie
import com.lpirro.cryptomovies.domain.model.WatchlistMovie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieList: List<Movie>)

    @Query("SELECT * FROM movie_table WHERE category=:category")
    fun getMoviesListWithCategory(category: Category): List<Movie>

    @Query("SELECT * FROM movie_table WHERE id=:movieId")
    fun getMovie(movieId: Long): Movie

    @Query("INSERT INTO watchlist_table SELECT * FROM movie_table WHERE id=:movieId")
    suspend fun insertToWatchlist(movieId: Long)

    @Query("SELECT * FROM watchlist_table")
    fun getWatchlist(): List<WatchlistMovie>
}
