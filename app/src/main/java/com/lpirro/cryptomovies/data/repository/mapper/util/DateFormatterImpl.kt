package com.lpirro.cryptomovies.data.repository.mapper.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class DateFormatterImpl : DateFormatter {
    override fun formatDate(date: String): String {
        return try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date)
            val newDateFormat = SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH)
            simpleDateFormat?.let { newDateFormat.format(it) } ?: "-"
        } catch (parseException: ParseException) {
            "-"
        }
    }
}
