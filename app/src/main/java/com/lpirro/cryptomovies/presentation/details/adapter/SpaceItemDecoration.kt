package com.lpirro.cryptomovies.presentation.details.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lpirro.cryptomovies.R

class SpaceItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                left = parent.context.resources.getDimensionPixelSize(R.dimen.margin_16dp)
                right = parent.context.resources.getDimensionPixelSize(R.dimen.margin_5dp)
            } else {
                left = parent.context.resources.getDimensionPixelSize(R.dimen.margin_5dp)
                right = parent.context.resources.getDimensionPixelSize(R.dimen.margin_5dp)
            }
        }
    }
}
