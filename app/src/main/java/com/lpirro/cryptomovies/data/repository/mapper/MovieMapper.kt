package com.lpirro.cryptomovies.data.repository.mapper

import com.lpirro.cryptomovies.data.network.model.MovieDto
import com.lpirro.cryptomovies.domain.model.Category
import com.lpirro.cryptomovies.domain.model.Movie

interface MovieMapper {
    fun mapDtoToEntity(movieDto: MovieDto, category: Category?): Movie
}
