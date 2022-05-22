package com.lpirro.cryptomovies.presentation.home.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lpirro.cryptomovies.R

class MoviePosterItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = parent.context.resources.getDimensionPixelSize(R.dimen.margin_12dp)
                left = parent.context.resources.getDimensionPixelSize(R.dimen.margin_16dp)
                right = parent.context.resources.getDimensionPixelSize(R.dimen.margin_4dp)
            } else {
                top = parent.context.resources.getDimensionPixelSize(R.dimen.margin_12dp)
                left = parent.context.resources.getDimensionPixelSize(R.dimen.margin_4dp)
                right = parent.context.resources.getDimensionPixelSize(R.dimen.margin_4dp)
            }
        }
    }
}
