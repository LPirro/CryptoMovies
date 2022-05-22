package com.lpirro.cryptomovies.data.network.model

import com.google.gson.annotations.SerializedName

data class MoviesListDto(
    @SerializedName("results") val movies: List<MovieDto>
)

data class MovieDto(
    @SerializedName("id") val id: Long,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("release_date") val releaseDate: String
)
