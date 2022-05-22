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
data class Movie(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "posterUrl") val posterUrl: String,
    @ColumnInfo(name = "backdropPath") val backdropPath: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: String,
    @ColumnInfo(name = "category") val category: Category?
)

enum class Category {
    POPULAR,
    TOP_RATED,
    NOW_PLAYING,
    UPCOMING
}
