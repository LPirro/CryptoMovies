package com.lpirro.cryptomovies.data.repository.mapper.util

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DateFormatterTest {

    private lateinit var dateFormatter: DateFormatter

    @Before
    fun setup() {
        dateFormatter = DateFormatterImpl()
    }

    @Test
    fun formatsDateSuccessfully() {
        val dateToFormat = "2019-06-24"
        val result = dateFormatter.formatDate(dateToFormat)
        assertEquals(result, "Jun 24 2019")
    }

    @Test
    fun wrongFormattedDateWillReturnDash() {
        val dateToFormat = "2019"
        val result = dateFormatter.formatDate(dateToFormat)
        assertEquals(result, "-")
    }
}
