package com.lpirro.cryptomovies.data.repository.mapper.util

import java.text.NumberFormat
import java.util.Locale

class CurrencyFormatterImpl : CurrencyFormatter {
    override fun formatToDollars(value: Int): String {
        if (value == 0) return "-"

        val format = NumberFormat.getCurrencyInstance(Locale.US)
        format.maximumFractionDigits = 0
        return format.format(value)
    }
}
