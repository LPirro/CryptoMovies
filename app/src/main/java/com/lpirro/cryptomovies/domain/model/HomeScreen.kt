package com.lpirro.cryptomovies.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class HomeScreen(
    val popularMovies: List<Movie>,
    val topRatedMovies: List<Movie>,
    val nowPlayingMovies: List<Movie>,
    val upcomingMovies: List<Movie>
)

@Entity(tableName = "movie_table")
open class Movie(
    @PrimaryKey @ColumnInfo(name = "id") open val id: Long,
    @ColumnInfo(name = "title") open val title: String,
    @ColumnInfo(name = "posterUrl") open val posterUrl: String,
    @ColumnInfo(name = "backdropPath") open val backdropPath: String,
    @ColumnInfo(name = "releaseDate") open val releaseDate: String,
    @ColumnInfo(name = "category") open val category: Category?,
)

enum class Category {
    POPULAR,
    TOP_RATED,
    NOW_PLAYING,
    UPCOMING
}
