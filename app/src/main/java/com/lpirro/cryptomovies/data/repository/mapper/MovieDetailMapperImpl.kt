package com.lpirro.cryptomovies.data.repository.mapper

import com.lpirro.cryptomovies.data.network.model.MovieDetailsDto
import com.lpirro.cryptomovies.domain.model.MovieDetail

class MovieDetailMapperImpl : MovieDetailMapper {
    override fun mapDtoToEntity(movieDetails: MovieDetailsDto) = MovieDetail(
        overview = movieDetails.overview,
        genres = movieDetails.genres.map { it.name },
        castImages = movieDetails.credits.cast.map {
            "https://image.tmdb.org/t/p/w500/${it.profileImageUrl}"
        },
        originalTitle = movieDetails.originalTitle,
        status = movieDetails.status,
        originalLanguage = movieDetails.originalLanguage,
        productionCompanies = movieDetails.productionCompanies.map { it.name },
        budget = movieDetails.budget.toString(),
        revenue = movieDetails.revenue.toString()
    )
}
