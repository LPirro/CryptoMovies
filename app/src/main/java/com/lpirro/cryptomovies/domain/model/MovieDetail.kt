package com.lpirro.cryptomovies.domain.model

data class MovieDetail(
    val overview: String,
    val genres: List<String>,
    val castImages: List<String>,
    val originalTitle: String,
    val status: String,
    val originalLanguage: String,
    val budget: String,
    val revenue: String
)
