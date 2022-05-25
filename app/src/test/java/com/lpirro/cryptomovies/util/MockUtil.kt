package com.lpirro.cryptomovies.util

import com.lpirro.cryptomovies.data.network.model.*
import com.lpirro.cryptomovies.domain.model.Category
import com.lpirro.cryptomovies.domain.model.HomeScreen
import com.lpirro.cryptomovies.domain.model.Movie
import com.lpirro.cryptomovies.domain.model.MovieDetail
import com.lpirro.cryptomovies.domain.model.WatchlistMovie

object MockUtil {

    fun mockMovieList(): List<Movie> {
        return listOf(
            Movie(
                9435,
                "Spider-Man",
                "https://image.tmdb.org/t/p/w500/image.jpg",
                "https://image.tmdb.org/t/p/w500/image.jpg",
                "2022-03-01",
                Category.POPULAR
            ),
            Movie(
                9432,
                "The Tourist",
                "https://image.tmdb.org/t/p/w500/image.jpg",
                "https://image.tmdb.org/t/p/w500/image.jpg",
                "2022-03-01",
                Category.NOW_PLAYING
            ),
            Movie(
                3424,
                "Ironman",
                "https://image.tmdb.org/t/p/w500/image.jpg",
                "https://image.tmdb.org/t/p/w500/image.jpg",
                "2022-03-01",
                Category.TOP_RATED
            ),
            Movie(
                1312,
                "Palla",
                "https://image.tmdb.org/t/p/w500/image.jpg",
                "https://image.tmdb.org/t/p/w500/image.jpg",
                "2022-03-01",
                Category.UPCOMING
            )
        )
    }

    fun mockMovieListDto() = MoviesListDto(getMovieListDto())

    fun mockHomeScreen() = HomeScreen(
        popularMovies = mockMovieList().filter { it.category == Category.POPULAR },
        upcomingMovies = mockMovieList().filter { it.category == Category.UPCOMING },
        nowPlayingMovies = mockMovieList().filter { it.category == Category.NOW_PLAYING },
        topRatedMovies = mockMovieList().filter { it.category == Category.TOP_RATED }
    )

    fun mockMovieDetail() = MovieDetail(
        overview = "Overview",
        genres = listOf("Action", "Thriller"),
        castImages = listOf(),
        originalTitle = "Ironman 3",
        status = "Released",
        originalLanguage = "En",
        budget = "$1,000,000",
        revenue = "$50,000,000"
    )

    private fun getMovieListDto(): List<MovieDto> {
        return listOf(
            MovieDto(
                31232,
                "Spider-Man",
                "/image.jpg",
                "/image.jpg",
                "2022-03-01"
            ),
            MovieDto(
                31232,
                "The Tourist",
                "/image.jpg",
                "/image.jpg",
                "2022-03-01"
            ),
            MovieDto(
                31232,
                "Ironman",
                "/image.jpg",
                "/image.jpg",
                "2022-03-01"
            ),
            MovieDto(
                31232,
                "Palla",
                "/image.jpg",
                "/image.jpg",
                "2022-03-01"
            )
        )
    }

    fun mockWatchlistMovies() = listOf(
        WatchlistMovie(
            id = 22131,
            title = "Batman",
            posterUrl = "https://image.tmdb.org/t/p/w500/image.jpg",
            backdropPath = "https://image.tmdb.org/t/p/w500/image.jpg",
            releaseDate = "2022-03-01",
            category = Category.TOP_RATED
        )
    )

    fun mockMovieDetailDto() = MovieDetailsDto(
        overview = "overview",
        genres = listOf(mockGenresDto()),
        credits = mockCreditsDto(),
        originalTitle = "Ironman 3",
        status = "Released",
        originalLanguage = "En",
        budget = 1000000,
        revenue = 500000
    )

    private fun mockCreditsDto() = CreditsDto(
        cast = listOf(
            CastDto("https://image.tmdb.org/t/p/w500/image.jpg")
        )
    )

    private fun mockGenresDto() = GenresDto(
        id = 1,
        "Action"
    )
}
