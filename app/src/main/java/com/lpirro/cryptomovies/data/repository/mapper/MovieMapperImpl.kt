package com.lpirro.cryptomovies.data.repository.mapper

import com.lpirro.cryptomovies.data.network.model.MovieDto
import com.lpirro.cryptomovies.data.repository.mapper.util.DateFormatter
import com.lpirro.cryptomovies.data.repository.mapper.util.ImageUrlProvider
import com.lpirro.cryptomovies.domain.model.Category
import com.lpirro.cryptomovies.domain.model.Movie

class MovieMapperImpl(
    private val imageUrlProvider: ImageUrlProvider,
    private val dateFormatter: DateFormatter
) : MovieMapper {
    override fun mapDtoToEntity(movieDto: MovieDto, category: Category?) = Movie(
        id = movieDto.id,
        title = movieDto.title,
        posterUrl = imageUrlProvider.provideFullUrl(movieDto.posterPath),
        backdropPath = imageUrlProvider.provideFullUrl(movieDto.backdropPath),
        releaseDate = dateFormatter.formatDate(movieDto.releaseDate),
        category = category
    )
}
