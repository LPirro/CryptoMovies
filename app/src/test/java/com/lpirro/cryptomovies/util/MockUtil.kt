package com.lpirro.cryptomovies.util

import com.lpirro.cryptomovies.data.network.model.MovieDto
import com.lpirro.cryptomovies.data.network.model.MoviesListDto
import com.lpirro.cryptomovies.domain.model.Category
import com.lpirro.cryptomovies.domain.model.Movie

object MockUtil {

    fun mockMovieList(): List<Movie> {
        return listOf(
            Movie(
                31232,
                "Spider-Man",
                "www.image.url",
                "www.poster.url",
                "2022-03-01",
                Category.POPULAR
            ),
            Movie(
                31232,
                "The Tourist",
                "www.image.url",
                "www.poster.url",
                "2022-03-01",
                Category.NOW_PLAYING
            ),
            Movie(
                31232,
                "Ironman",
                "www.image.url",
                "www.poster.url",
                "2022-03-01",
                Category.TOP_RATED
            ),
            Movie(
                31232,
                "Palla",
                "www.image.url",
                "www.poster.url",
                "2022-03-01",
                Category.UPCOMING
            )
        )
    }

    fun mockMovieListDto() = MoviesListDto(getMovieListDto())

    private fun getMovieListDto(): List<MovieDto> {
        return listOf(
            MovieDto(
                31232,
                "Spider-Man",
                "www.image.url",
                "www.poster.url",
                "2022-03-01"
            ),
            MovieDto(
                31232,
                "The Tourist",
                "www.image.url",
                "www.poster.url",
                "2022-03-01"
            ),
            MovieDto(
                31232,
                "Ironman",
                "www.image.url",
                "www.poster.url",
                "2022-03-01"
            ),
            MovieDto(
                31232,
                "Palla",
                "www.image.url",
                "www.poster.url",
                "2022-03-01"
            )
        )
    }

}
