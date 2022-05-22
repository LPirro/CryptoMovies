package com.lpirro.cryptomovies.data.repository.mapper

import com.lpirro.cryptomovies.data.network.model.MovieDetailsDto
import com.lpirro.cryptomovies.domain.model.MovieDetail
import java.text.NumberFormat

class MovieDetailMapperImpl : MovieDetailMapper {
    override fun mapDtoToEntity(movieDetails: MovieDetailsDto) = MovieDetail(
        overview = movieDetails.overview,
        genres = movieDetails.genres.map { it.name },
        castImages = movieDetails.credits.cast.map {
            "https://image.tmdb.org/t/p/w500/${it.profileImageUrl}" // TODO Remove it
        },
        originalTitle = movieDetails.originalTitle,
        status = movieDetails.status,
        originalLanguage = movieDetails.originalLanguage.uppercase(),
        budget = movieDetails.budget.toDollars(),
        revenue = movieDetails.revenue.toDollars()
    )

    private fun Int.toDollars(): String {
        if (this == 0) return "-"

        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        return format.format(this)
    }
}
