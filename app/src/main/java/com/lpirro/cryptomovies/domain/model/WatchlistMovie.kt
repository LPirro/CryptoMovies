package com.lpirro.cryptomovies.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watchlist_table")
class WatchlistMovie(
    @PrimaryKey @ColumnInfo(name = "id") override val id: Long,
    @ColumnInfo(name = "title") override val title: String,
    @ColumnInfo(name = "posterUrl") override val posterUrl: String,
    @ColumnInfo(name = "backdropPath") override val backdropPath: String,
    @ColumnInfo(name = "releaseDate") override val releaseDate: String,
    @ColumnInfo(name = "category") override val category: Category?,
) : Movie(id, title, posterUrl, backdropPath, releaseDate, category)
