package com.lpirro.cryptomovies.data.repository.mapper

import com.lpirro.cryptomovies.data.repository.mapper.util.DateFormatter
import com.lpirro.cryptomovies.data.repository.mapper.util.ImageUrlProvider
import com.lpirro.cryptomovies.domain.model.Category
import com.lpirro.cryptomovies.domain.model.Movie
import com.lpirro.cryptomovies.util.MockUtil
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MovieMapperTest {

    private lateinit var movieMapper: MovieMapper
    private var imageUrlProvider: ImageUrlProvider = mock()
    private var dateFormatter: DateFormatter = mock()

    @Before
    fun setup() {
        movieMapper = MovieMapperImpl(imageUrlProvider, dateFormatter)
    }

    @Test
    fun movieDtoMapsCorrectly() {
        val imageMockedPathUrl = "https://image.tmdb.org/t/p/w500/image.jpg"
        val mockedDate = "Jan 01 2022"
        val mockedMovieDto = MockUtil.mockMovieListDto().movies.first()
        whenever(imageUrlProvider.provideFullUrl(any())).thenReturn(imageMockedPathUrl)
        whenever(dateFormatter.formatDate(any())).thenReturn(mockedDate)

        val result = movieMapper.mapDtoToEntity(mockedMovieDto, Category.TOP_RATED)

        val expectedMovie = Movie(
            id = 31232,
            title = "Spider-Man",
            posterUrl = imageMockedPathUrl,
            backdropPath = imageMockedPathUrl,
            releaseDate = mockedDate,
            category = Category.TOP_RATED
        )

        assertEquals(result.id, expectedMovie.id)
        assertEquals(result.title, expectedMovie.title)
        assertEquals(result.posterUrl, expectedMovie.posterUrl)
        assertEquals(result.backdropPath, expectedMovie.backdropPath)
        assertEquals(result.releaseDate, expectedMovie.releaseDate)
        assertEquals(result.category, expectedMovie.category)
    }
}
