package com.lpirro.cryptomovies.presentation.details.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.flexbox.FlexboxItemDecoration
import com.lpirro.cryptomovies.R

class GenresItemDecoration(val context: Context) : FlexboxItemDecoration(context) {

    init {
        val paddingDrawable = ContextCompat.getDrawable(context, R.drawable.flexbox_custom_padding)
        setDrawable(paddingDrawable)
    }
}
