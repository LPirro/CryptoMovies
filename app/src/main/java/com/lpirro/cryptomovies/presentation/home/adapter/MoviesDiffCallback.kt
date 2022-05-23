package com.lpirro.cryptomovies.presentation.home.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.lpirro.cryptomovies.domain.model.Movie

class MoviesDiffCallback(
    private val oldList: List<Movie>,
    private val newList: List<Movie>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title === newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val title = oldList[oldPosition].title
        val newTitle = newList[newPosition].title
        return title == newTitle
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}
