package com.lpirro.cryptomovies.data.repository.mapper

import com.lpirro.cryptomovies.data.network.model.MovieDetailsDto
import com.lpirro.cryptomovies.domain.model.MovieDetail

interface MovieDetailMapper {
    fun mapDtoToEntity(movieDetails: MovieDetailsDto): MovieDetail
}
