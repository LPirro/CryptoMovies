package com.lpirro.cryptomovies.data.repository.mapper

import com.lpirro.cryptomovies.data.repository.mapper.util.CurrencyFormatter
import com.lpirro.cryptomovies.data.repository.mapper.util.ImageUrlProvider
import com.lpirro.cryptomovies.domain.model.MovieDetail
import com.lpirro.cryptomovies.util.MockUtil
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MovieDetailMapperTest {

    private lateinit var movieDetailMapper: MovieDetailMapper
    private var imageUrlProvider: ImageUrlProvider = mock()
    private val currencyFormatter: CurrencyFormatter = mock()

    @Before
    fun setup() {
        movieDetailMapper = MovieDetailMapperImpl(imageUrlProvider, currencyFormatter)
    }

    @Test
    fun movieDetailDtoMapsCorrectly() {
        val imageMockedPathUrl = "https://image.tmdb.org/t/p/w500/image.jpg"
        val mockedCurrencyValue = "$ 1,000,000"
        val mockedMovieDetailDto = MockUtil.mockMovieDetailDto()
        whenever(imageUrlProvider.provideFullUrl(any())).thenReturn(imageMockedPathUrl)
        whenever(currencyFormatter.formatToDollars(any())).thenReturn(mockedCurrencyValue)

        val result = movieDetailMapper.mapDtoToEntity(mockedMovieDetailDto)

        val expectedMovieDetail = MovieDetail(
            overview = "overview",
            genres = listOf("Action"),
            castImages = listOf(imageMockedPathUrl),
            originalTitle = "Ironman 3",
            status = "Released",
            originalLanguage = "EN",
            budget = mockedCurrencyValue,
            revenue = mockedCurrencyValue
        )

        assertEquals(result, expectedMovieDetail)
    }
}
