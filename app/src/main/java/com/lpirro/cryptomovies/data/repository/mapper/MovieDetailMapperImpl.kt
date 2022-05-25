package com.lpirro.cryptomovies.data.repository.mapper

import com.lpirro.cryptomovies.data.network.model.MovieDetailsDto
import com.lpirro.cryptomovies.data.repository.mapper.util.CurrencyFormatter
import com.lpirro.cryptomovies.data.repository.mapper.util.ImageUrlProvider
import com.lpirro.cryptomovies.domain.model.MovieDetail

class MovieDetailMapperImpl(
    private val imageUrlProvider: ImageUrlProvider,
    private val currencyFormatter: CurrencyFormatter
) : MovieDetailMapper {
    override fun mapDtoToEntity(movieDetails: MovieDetailsDto) = MovieDetail(
        overview = movieDetails.overview,
        genres = movieDetails.genres.map { it.name },
        castImages = movieDetails.credits.cast.map {
            imageUrlProvider.provideFullUrl(it.profileImageUrl)
        },
        originalTitle = movieDetails.originalTitle,
        status = movieDetails.status,
        originalLanguage = movieDetails.originalLanguage.uppercase(),
        budget = currencyFormatter.formatToDollars(movieDetails.budget),
        revenue = currencyFormatter.formatToDollars(movieDetails.revenue)
    )
}
