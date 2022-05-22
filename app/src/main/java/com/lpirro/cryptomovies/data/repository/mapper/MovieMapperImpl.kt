package com.lpirro.cryptomovies.data.repository.mapper

import com.lpirro.cryptomovies.data.network.model.MovieDto
import com.lpirro.cryptomovies.domain.model.Category
import com.lpirro.cryptomovies.domain.model.Movie
import java.text.SimpleDateFormat
import java.util.Locale

class MovieMapperImpl : MovieMapper {
    override fun mapDtoToEntity(movieDto: MovieDto, category: Category?) = Movie(
        id = movieDto.id,
        title = movieDto.title,
        posterUrl = "https://image.tmdb.org/t/p/w500/${movieDto.posterPath}",
        backdropPath = "https://image.tmdb.org/t/p/w500/${movieDto.backdropPath}",
        releaseDate = movieDto.releaseDate.formatDate(),
        category = category
    )

    private fun String.formatDate(): String {
        val date = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(this)
        val newDateFormat = SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH)
        return date?.let { newDateFormat.format(it) } ?: this
    }
}
