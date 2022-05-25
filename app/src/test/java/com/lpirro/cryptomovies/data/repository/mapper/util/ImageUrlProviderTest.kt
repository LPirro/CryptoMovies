package com.lpirro.cryptomovies.data.repository.mapper.util

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ImageUrlProviderTest {

    private lateinit var imageUrlProvider: ImageUrlProvider

    @Before
    fun setup() {
        imageUrlProvider = ImageUrlProviderImpl()
    }

    @Test
    fun imageProviderWillProvideFullUrlSuccessfully() {
        val result = imageUrlProvider.provideFullUrl("/image.jpg")
        val expectedResult = "https://image.tmdb.org/t/p/w500/image.jpg"
        assertEquals(result, expectedResult)
    }
}
