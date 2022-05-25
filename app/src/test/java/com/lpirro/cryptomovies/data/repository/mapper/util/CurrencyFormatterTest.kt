package com.lpirro.cryptomovies.data.repository.mapper.util

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CurrencyFormatterTest {

    lateinit var currencyFormatter: CurrencyFormatter

    @Before
    fun setup() {
        currencyFormatter = CurrencyFormatterImpl()
    }

    @Test
    fun formatsToDollarsSuccessfully() {
        val result = currencyFormatter.formatToDollars(7000000)
        assertEquals(result, "$7,000,000")
    }

    @Test
    fun emptyValueWillReturnDash() {
        val result = currencyFormatter.formatToDollars(0)
        assertEquals(result, "-")
    }
}
