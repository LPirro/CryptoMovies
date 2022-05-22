package com.lpirro.cryptomovies.data.peristance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lpirro.cryptomovies.domain.model.Category
import com.lpirro.cryptomovies.domain.model.Movie

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieList: List<Movie>)

    @Query("SELECT * FROM movie_table")
    suspend fun getMoviesList(): List<Movie>

    @Query("SELECT * FROM movie_table WHERE category=:category")
    fun getMoviesListWithCategory(category: Category): List<Movie>

    @Query("SELECT * FROM movie_table WHERE id=:movieId")
    fun getMovie(movieId: Long): Movie
}
