package com.lpirro.cryptomovies.data.repository.mapper.util

interface CurrencyFormatter {
    fun formatToDollars(value: Int): String
}
